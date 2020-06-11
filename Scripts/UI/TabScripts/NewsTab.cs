using static UIContainer;

public class NewsTab : Tab
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
        UIEventSystem.CurrentPanel = newsPanel;
        newsPanel.SetActive(true);
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        newsPanel.SetActive(false);
    }
}
