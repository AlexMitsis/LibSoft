using UnityEngine;
using static UIContainer;

public class MainMenu : MonoBehaviour
{
    public static MainMenu Instance { get; set; }

    private static UserInput input;

    private void Awake()
    {
        Singleton();

        input = new UserInput();

        input.UI.ChangeTab.performed += _ => ChangeTab();
        input.Enable();
    }

    //Using Start() for initialization:
    private void Start()
    {
        SwitchToLoginMode();
        borrowerAppScreen.SetActive(false);
    }

    private void Singleton()
    {
        if (Instance != null && Instance != this)
            Destroy(gameObject);
        else Instance = this;
    }

    private void SwitchToLoginMode()
    {
        entryButton.Label.text = "Login";
        entryButton.onClick.RemoveAllListeners();
        entryButton.onClick.AddListener(delegate { Login(); });

        changeModeButton.Label.text = "Register";
        changeModeButton.onClick.RemoveAllListeners();
        changeModeButton.onClick.AddListener(SwitchToRegisterMode);

        signInQuestionText.text = "Don't have an account?";
        usernameInputField.text = string.Empty;
        passwordInputField.text = string.Empty;
    }

    private void SwitchToRegisterMode()
    {
        entryButton.Label.text = "Register";
        entryButton.onClick.RemoveAllListeners();
        entryButton.onClick.AddListener(Register);

        changeModeButton.Label.text = "Login";
        changeModeButton.onClick.RemoveAllListeners();
        changeModeButton.onClick.AddListener(SwitchToLoginMode);

        signInQuestionText.text = "Already have an account?";
        usernameInputField.text = string.Empty;
        passwordInputField.text = string.Empty;
    }

    public void ChangeTab()
    {
        if (usernameInputField.isFocused)
            UIEventSystem.eventSystem.SetSelectedGameObject(passwordInputField.gameObject);
        else if(passwordInputField.isFocused)
            UIEventSystem.eventSystem.SetSelectedGameObject(usernameInputField.gameObject);
    }

    #region Button OnClick Events:
    private void Login(bool successfulRegistration = false)
    {
        if (usernameInputField.text.Equals(string.Empty) || passwordInputField.text.Equals(string.Empty))
        {
            StartCoroutine(UIEventSystem.ActionDeniedNotification("Please enter a valid username and password."));
            return;
        }

        if (!AccountManager.AccountExists(usernameInputField.text) || AccountManager.accounts.Count <= 0)
        {
            StartCoroutine(UIEventSystem.ActionDeniedNotification("Account not found! Please, register before you log in."));
            return;
        }

        bool matched = successfulRegistration;

        if (AccountManager.IsLibrarianLogin(usernameInputField.text, passwordInputField.text))
        {
            for (int i = 0; i < AccountManager.accounts.Count; i++)
            {
                LibraryMember acc = AccountManager.accounts[i];

                if (acc is Librarian)
                {
                    AccountManager.currentAccount = acc;
                    break;
                }
            }

            mainMenuHolder.SetActive(false);
            optionsTab.gameObject.SetActive(true);
            librarianAppScreen.SetActive(true);
            UIEventSystem.SelectNewTab(postTab);
            return;
        }
        else
        {
            for (int i = 0; i < AccountManager.accounts.Count; i++)
            {
                LibraryMember acc = AccountManager.accounts[i];

                if (acc.username.Equals(usernameInputField.text) && acc.password.Equals(passwordInputField.text))
                {
                    matched = true;
                    AccountManager.currentAccount = acc;
                    break;
                }
            }
        }

        if (matched)
        {
            mainMenuHolder.SetActive(false);
            borrowerAppScreen.SetActive(true);
            optionsTab.gameObject.SetActive(true);

            Member m = (Member)AccountManager.currentAccount;
            m.LoadSavedData();

            UsernameTab tab = (UsernameTab)usernameTab;
            tab.usernameLabel.text = m.username.ToUpper();

            UIEventSystem.SelectNewTab(usernameTab);
        }
        else StartCoroutine(UIEventSystem.ActionDeniedNotification("Incorrect Password or Username."));
    }

    private void Register()
    {
        if (usernameInputField.text.Equals(string.Empty) || passwordInputField.text.Equals(string.Empty))
        {
            StartCoroutine(UIEventSystem.ActionDeniedNotification("Please enter a valid username and password."));
            return;
        }

        if (AccountManager.AccountExists(usernameInputField.text))
        {
            StartCoroutine(UIEventSystem.ActionDeniedNotification("This account already exists. Please, login instead."));
            return;
        }

        LibraryMember m = AccountManager.InstantiateMember(AccountManager.memberPrefab, usernameInputField.text, passwordInputField.text);
        AccountManager.AddMember(m);

        Login(true);
    }
    #endregion
}
