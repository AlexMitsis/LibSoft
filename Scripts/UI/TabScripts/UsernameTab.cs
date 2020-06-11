using static UIContainer;
using UnityEngine.UI;

public class UsernameTab : Tab
{
    public Text usernameLabel;

    protected override void Awake()
    {
        base.Awake();
        usernameLabel = transform.GetChild(0).GetComponent<Text>();
    }

    public override void OnPointerEnter()
    {
        base.OnPointerEnter();
    }

    public override void OnPointerExit()
    {
        base.OnPointerExit();
    }

    public override void OnSelect()
    {
        base.OnSelect();
        personalLibraryPanel.SetActive(true);
        UIEventSystem.CurrentPanel = personalLibraryPanel;

        Member m = (Member)AccountManager.currentAccount;

        emptyLibraryNotification.gameObject.SetActive(m.borrowedBooks.Count == 0);
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        personalLibraryPanel.SetActive(false);
    }
}
