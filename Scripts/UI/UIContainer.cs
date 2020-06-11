using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Playables;
using UnityEngine.UI;

public class UIContainer : MonoBehaviour
{
    public static UIContainer UI { get; set; }

    public static GameObject mainMenuHolder, borrowerAppScreen, librarianAppScreen;
    public static ImprovedButton entryButton, changeModeButton;
    public static Text signInQuestionText;
    public static InputField usernameInputField, passwordInputField;
    public static Button submitCommentButton;
    public static Tab usernameTab, browseTab, favoritesTab, toReadTab, newsTab , contactTab, optionsTab, notificationsTab, monitorTab, postTab;
    public static Text emptyLibraryNotification;
    public static GameObject searchBar, bookPagePanel;
    public static Text bookPage_Title, bookPage_Description;
    public static Image bookPage_Cover;
    public static Button bookPage_BackButton;
    public static GameObject newsPagePanel;
    public static Button newsPage_BackButton;
    public static Button logoutButton, exitToDesktopButton;
    public static ImprovedButton borrowButton, addToFavoritesButton, readLaterButton;
    public static GameObject favoritesLayoutGroup;
    public static GameObject personalLibraryLayoutGroup;
    public static GameObject browseLayoutGroup;
    public static GameObject toReadLayoutGroup;
    public static GameObject newsLayoutGroup;
    public static GameObject personalLibraryPanel, browsePanel, favoritesPanel, toReadPanel, newsPanel, contactPanel, optionsPanel;
    public static Text recentAdditionsText;
    public static GameObject actionDeniedNotificationPanel;
    public static Text actionDeniedNotificationText;
    public static InputField commentInputField;
    public static GameObject commentsLayoutGroup;
    public static GameObject newsPostingPanel;
    public static Text newsPagePanel_Header;
    public static Text newsPagePanel_ArticleText;
    public static Button postNewsArticleButton;
    public static InputField headerInputField, articleInputField;
    public static GameObject memberList;
    public static GameObject monitoringPanel;
    public static GameObject monitoring_BorrowedBooks;

    public List<Book> books = new List<Book>();
    
    private void Awake()
    {
        Singleton();
        InitializeElements();
    }

    private void Singleton()
    {
        if (UI != null && UI != this) Destroy(gameObject);
        else UI = this;
    }

    private void InitializeElements()
    {
        mainMenuHolder = GameObject.Find("MainMenuScreen");
        personalLibraryPanel = GameObject.Find("PersonalLibraryPanel");
        browsePanel = GameObject.Find("BrowsePanel");
        favoritesPanel = GameObject.Find("FavoritesPanel");
        submitCommentButton = GameObject.Find("SubmitCommentButton").GetComponent<Button>();
        commentInputField = GameObject.Find("CommentInputField").GetComponent<InputField>();
        optionsPanel = GameObject.Find("OptionsPanel");
        toReadPanel = GameObject.Find("ToReadPanel");
        newsPanel = GameObject.Find("NewsPanel");
        contactPanel = GameObject.Find("ContactPanel");
        borrowerAppScreen = GameObject.Find("BorrowerAppScreen");
        librarianAppScreen = GameObject.Find("LibrarianAppScreen");
        searchBar = GameObject.Find("SearchBar");
        logoutButton = GameObject.Find("LogoutButton").GetComponent<Button>();
        exitToDesktopButton = GameObject.Find("ExitToDesktopButton").GetComponent<Button>();
        bookPagePanel = GameObject.Find("BookPagePanel");
        bookPage_BackButton = GameObject.Find("BookInfo").transform.Find("BookPage_BackButton").GetComponent<Button>();
        bookPage_Description = GameObject.Find("BookPage_Description").GetComponent<Text>();
        bookPage_Title = GameObject.Find("BookPage_Title").GetComponent<Text>();
        bookPage_Cover = GameObject.Find("BookPage_Cover").GetComponent<Image>();
        borrowButton = GameObject.Find("BorrowButton").GetComponent<ImprovedButton>();
        borrowButton.Label = borrowButton.transform.GetChild(0).GetComponent<Text>();
        addToFavoritesButton = GameObject.Find("AddToFavoritesButton").GetComponent<ImprovedButton>();
        addToFavoritesButton.Label = addToFavoritesButton.transform.GetChild(0).GetComponent<Text>();
        readLaterButton = GameObject.Find("ReadLaterButton").GetComponent<ImprovedButton>();
        readLaterButton.Label = readLaterButton.transform.GetChild(0).GetComponent<Text>();
        recentAdditionsText = GameObject.Find("RecentAdditions").GetComponent<Text>();
        favoritesLayoutGroup = GameObject.Find("FavoritesContent").transform.GetChild(0).gameObject;
        browseLayoutGroup = GameObject.Find("BrowseContent").transform.GetChild(0).gameObject;
        toReadLayoutGroup = GameObject.Find("ToReadContent").transform.GetChild(0).gameObject;
        personalLibraryLayoutGroup = GameObject.Find("PersonalLibraryContent").transform.GetChild(0).gameObject;
        newsLayoutGroup = GameObject.Find("NewsLayoutGroup");
        commentsLayoutGroup = GameObject.Find("CommentsLayoutGroup");
        newsPostingPanel = GameObject.Find("NewsPostingPanel");
        headerInputField = newsPostingPanel.transform.GetChild(4).transform.GetChild(0).GetComponent<InputField>();
        articleInputField = newsPostingPanel.transform.GetChild(3).transform.GetChild(0).GetComponent<InputField>();
        newsPagePanel = GameObject.Find("NewsPagePanel");
        postNewsArticleButton = newsPostingPanel.transform.Find("Button").GetComponent<Button>();
        newsPage_BackButton = newsPagePanel.transform.GetChild(0).GetComponent<Button>();
        memberList = GameObject.Find("MemberList");
        monitoringPanel = GameObject.Find("MonitoringPanel");
        monitoring_BorrowedBooks = GameObject.Find("Monitoring_BorrowedBooks");

        Transform articlePage = newsPagePanel.transform.Find("ArticlePage").transform;

        newsPagePanel_Header = articlePage.transform.GetChild(0).GetComponent<Text>();
        newsPagePanel_ArticleText = articlePage.transform.GetChild(1).GetComponent<Text>();

        emptyLibraryNotification = GameObject.Find("EmptyLibraryNotif").GetComponent<Text>();
        actionDeniedNotificationPanel = GameObject.Find("ActionDeniedNotificationPanel");
        actionDeniedNotificationText = actionDeniedNotificationPanel.transform.GetChild(0).GetComponent<Text>();

        GameObject mainMenu = mainMenuHolder.transform.GetChild(0).gameObject; //Get the MainMenu gameObject
        //from the mainMenuHolder parent so we can access its own children.

        usernameInputField = mainMenu.transform.Find("UsernameInputField").GetComponent<InputField>();
        passwordInputField = mainMenu.transform.Find("PasswordInputField").GetComponent<InputField>();

        entryButton = mainMenu.transform.Find("EntryButton").GetComponent<ImprovedButton>();
        entryButton.Label = entryButton.transform.GetChild(0).GetComponent<Text>();
        changeModeButton = mainMenu.transform.Find("ChangeModeButton").GetComponent<ImprovedButton>();
        changeModeButton.Label = changeModeButton.transform.GetChild(0).GetComponent<Text>();

        signInQuestionText = mainMenu.transform.Find("SignInQuestionText").GetComponent<Text>();

        usernameTab = FindObjectOfType<UsernameTab>();
        browseTab = FindObjectOfType<BrowseTab>();
        favoritesTab = FindObjectOfType<FavoritesTab>();
        toReadTab = FindObjectOfType<ToReadTab>();
        newsTab = FindObjectOfType<NewsTab>();
        contactTab = FindObjectOfType<ContactTab>();
        optionsTab = FindObjectOfType<OptionsTab>();
        //notificationsTab = FindObjectOfType<NotificationsTab>();
        monitorTab = FindObjectOfType<MonitorTab>();
        postTab = FindObjectOfType<PostTab>();

        SetUp();
    }

    private void SetUp()
    {
        usernameInputField.characterValidation = InputField.CharacterValidation.Alphanumeric;
        passwordInputField.characterValidation = InputField.CharacterValidation.Alphanumeric;
        searchBar.SetActive(false);
        borrowerAppScreen.SetActive(false);
        personalLibraryPanel.SetActive(false);
        favoritesPanel.SetActive(false);
        toReadPanel.SetActive(false);
        contactPanel.SetActive(false);
        newsPanel.SetActive(false);
        bookPagePanel.SetActive(false);
        optionsPanel.SetActive(false);
        actionDeniedNotificationPanel.SetActive(false);
        newsPostingPanel.SetActive(false);
        librarianAppScreen.SetActive(false);
        optionsTab.gameObject.SetActive(false);
        monitoringPanel.SetActive(false);

        logoutButton.onClick.AddListener(AccountManager.LogMemberOut);
        exitToDesktopButton.onClick.AddListener(AccountManager.LogMemberOut);
        exitToDesktopButton.onClick.AddListener(Application.Quit);
        bookPage_BackButton.onClick.AddListener(UIEventSystem.BackToPreviousPanel);
        newsPage_BackButton.onClick.AddListener(UIEventSystem.BackToPreviousPanel);
        postNewsArticleButton.onClick.AddListener
        (delegate { Librarian.PostNewsArticle(headerInputField.text, articleInputField.text); });

        submitCommentButton.onClick.AddListener
        (
            delegate
            {
                UIEventSystem.LeaveComment(new Book.Comment(AccountManager.currentAccount.username,
                commentInputField.text, UIEventSystem.selectedBook.rating));
            }
        );
    }
}
