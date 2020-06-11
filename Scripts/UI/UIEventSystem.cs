//Lead Programmer: George Rontoulis
//Project: Libsoft - Libero (Library Management App)

//LAST EDITED: 10-6-2020 BY Alexandros Mitsis
//[PLEASE CHANGE THE ABOVE IF YOU EDIT THIS SCRIPT]

using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;
using System;
using static UIContainer;
using static RatingSystem;
using System.Collections;
using System.Collections.Generic;
using System.Security.Authentication.ExtendedProtection;
using System.Runtime.ExceptionServices;
using System.Xml.Serialization;

//This class handles all the events that fire through the UI.
public class UIEventSystem : MonoBehaviour
{
    #region Private Variables:
    private InputField searchBarField;
    [SerializeField] private int searchTolerance = 0; //Error tolerance for the search bar.
    #endregion

    #region Public Variables:
    public static UIEventSystem Instance { get; set; }
    public static EventSystem eventSystem; //Unity's EventSystem that handles selections.
    public static GameObject CurrentPanel { get; set; }
    public static Tab currentSelectedTab; //Currently selected tab by Unity's EventSystem;
    public static Tab lastSelectedTab; //Last selected tab by Unity's EventSystem;
    public static Book selectedBook; //Currently selected book by the user.
    #endregion

    //We use this method for initialization.
    private void Awake()
    {
        Singleton();

        eventSystem = GetComponent<EventSystem>();
    }

    private void Start()
    {
        searchBarField = searchBar.transform.GetChild(0).GetComponent<InputField>();

        searchBarField.onValueChanged.AddListener(delegate { UpdateBookList(); });

        searchTolerance = 11;
    }

    private void UpdateBookList()
    {
        if (!searchBarField) return;

        if (searchBarField.text != string.Empty)
        {
            recentAdditionsText.text = "SEARCH RESULTS";

            for (int i = 0; i < UI.books.Count; i++)
            {
                if (LevenshteinDistance(searchBarField.text, UI.books[i].bookData.title) <= searchTolerance
                    || LevenshteinDistance(searchBarField.text, UI.books[i].bookData.author) <= searchTolerance)
                {
                    UI.books[i].gameObject.SetActive(true);
                }
                else UI.books[i].gameObject.SetActive(false);
            }
        }
        else
        {
            recentAdditionsText.text = "RECENT ADDITIONS";

            for (int i = 0; i < UI.books.Count; i++)
            {
                UI.books[i].gameObject.SetActive(true);
            }
        }
    }

    /// <summary>
    /// Compute the distance between two strings.
    /// </summary>
    private static int LevenshteinDistance(string string1, string string2)
    {
        int stringLength1 = string1.Length;
        int stringLength2 = string2.Length;

        int[,] distance = new int[stringLength1 + 1, stringLength2 + 1];

        if (stringLength1 == 0) return stringLength2;

        if (stringLength2 == 0) return stringLength1;

        for (int i = 1; i <= stringLength1; i++)
        {
            for (int j = 1; j <= stringLength2; j++)
            {
                int cost = 1;

                if (string2[j - 1] == string1[i - 1]) cost = 0;

                distance[i, j] = Math.Min(Math.Min(distance[i - 1, j] + 1, distance[i, j - 1] + 1), distance[i - 1, j - 1] + cost);
            }
        }

        return distance[stringLength1, stringLength2];
    }

    //We use this method to make sure that there's only one Instance of this class at runtime.
    private void Singleton()
    {
        if (Instance != null && Instance != this)
            Destroy(gameObject);
        else 
            Instance = this;
    }

    public static void SelectNewTab(Tab newTab)
    {
        if (newTab.Equals(currentSelectedTab)) return;

        lastSelectedTab = currentSelectedTab;
        currentSelectedTab = newTab;

        if (lastSelectedTab) lastSelectedTab.OnDeselect();
        if (currentSelectedTab) currentSelectedTab.OnSelect();
    }

    public static void BackToPreviousPanel()
    {
        if(bookPagePanel.activeSelf) bookPagePanel.SetActive(false);
        if (newsPagePanel.activeSelf) newsPagePanel.SetActive(false);
        CurrentPanel.SetActive(true);
        selectedBook = null;
        currentRating = 0;

        if (CurrentPanel.Equals(browsePanel)) searchBar.SetActive(true);
    }

    public static IEnumerator ActionDeniedNotification(string text)
    {
        if (actionDeniedNotificationPanel.activeSelf) yield return null;

        actionDeniedNotificationText.text = text;
        actionDeniedNotificationPanel.SetActive(true);
        yield return new WaitForSeconds(2);
        actionDeniedNotificationPanel.SetActive(false);
    }

    public static void LeaveComment(Book.Comment comment)
    {
        if (comment.comment.Equals(string.Empty)) return;

        CommentTemplate commentTemplate = Instantiate(Resources.Load("BookCommentTemplate", typeof(CommentTemplate)), commentsLayoutGroup.transform) as CommentTemplate;

        commentTemplate.GetCommentData(comment);
        selectedBook.comments.Add(comment);
        commentInputField.text = string.Empty;
    }

    public static void SaveSystem()
    {
        SavingSystem.SaveSystemData();
    }

    public static void SaveMember()
    {
        if (AccountManager.currentAccount is Librarian) return;

        SavingSystem.SaveMemberData();
    }
}
