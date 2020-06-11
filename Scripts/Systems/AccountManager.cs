using System.Collections.Generic;
using UnityEngine;
using static UIContainer;

public sealed class AccountManager : MonoBehaviour
{
    public static AccountManager Instance { get; set; }

    public static Object librarianPrefab;
    public static Object memberPrefab;

    public static List<LibraryMember> accounts = new List<LibraryMember>();

    public static LibraryMember currentAccount;

    private void Start()
    {
        Singleton();

        librarianPrefab = Resources.Load("LibrarianPrefab", typeof(GameObject));
        memberPrefab = Resources.Load("MemberPrefab", typeof(GameObject));

        LibraryMember m = InstantiateMember(librarianPrefab, "Librarian", "Librarian");
        AddMember(m);

        LoadAccounts();
    }

    private void Singleton()
    {
        if (Instance != null && Instance != this)
            Destroy(gameObject);
        else Instance = this;
    }

    public static LibraryMember InstantiateMember(Object prefab, string username, string password)
    {
        GameObject instance = Instantiate(prefab) as GameObject;
        instance.name = username;

        LibraryMember m = instance.GetComponent<LibraryMember>();
        m.username = username;
        m.password = password;

        if (m is Librarian) return m;
        else
        {
            MemberListElement element = Instantiate(Resources.Load("MemberListElement", typeof(MemberListElement)), memberList.transform) as MemberListElement;
            element.GetElementInfo(m.username);
            return m;
        }

        
        
    }

    public static void AddMember(LibraryMember m)
    {
        accounts.Add(m);
    }

    public static void RemoveMember(LibraryMember m)
    {
        accounts.Remove(m);
    }

    private static void ClearPanel(Transform panel)
    {
        for (int i = 0; i < panel.childCount; i++)
        {
            Destroy(panel.transform.GetChild(i).gameObject);
        }
    }

    public static void LogMemberOut()
    {
        UIEventSystem.SaveSystem();
        UIEventSystem.SaveMember();

        if (!(currentAccount is Librarian))
        {
            Member m = (Member)currentAccount;
            m.ClearAllCollections();
        }
        
        ClearPanel(personalLibraryLayoutGroup.transform);
        ClearPanel(favoritesLayoutGroup.transform);
        ClearPanel(toReadLayoutGroup.transform);

        if(borrowerAppScreen.activeSelf) borrowerAppScreen.SetActive(false);
        if(librarianAppScreen) librarianAppScreen.SetActive(false);
        optionsPanel.SetActive(false);
        mainMenuHolder.SetActive(true);
        optionsTab.gameObject.SetActive(false);
        currentAccount = null;
    }

    public static bool AccountExists(string username)
    {
        for (int i = 0; i < accounts.Count; i++)
        {
            if (accounts[i].username.Equals(username))
            {
                return true;
            }
        }

        return false;
    }

    public static bool IsLibrarianLogin(string username, string password)
    {
        bool b1 = username == "Librarian";
        bool b2 = password == "Librarian";

        return b1 && b2;
    }

    private static void LoadAccounts()
    {
        SavedSystemData data = SavingSystem.LoadSystemData();

        if (data != null)
        {
            for (int i = 0; i < data.savedUsernames.Count; i++)
            {
                Member m = InstantiateMember(memberPrefab, data.savedUsernames[i], data.savedPasswords[i]) as Member;

                AddMember(m);
            }
        }
    }
}
