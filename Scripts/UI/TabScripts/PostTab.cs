using static UIContainer;

public class PostTab : Tab
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
        newsPostingPanel.SetActive(true);
        UIEventSystem.CurrentPanel = newsPostingPanel;
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        newsPostingPanel.SetActive(false);
    }
}
