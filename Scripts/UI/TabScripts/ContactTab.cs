using static UIContainer;

public class ContactTab : Tab
{
    protected override void Awake()
    {
        base.Awake();
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
        contactPanel.SetActive(true);
        UIEventSystem.CurrentPanel = contactPanel;
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        contactPanel.SetActive(false);
    }
}
