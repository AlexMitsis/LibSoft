using static UIContainer;

public class MonitorTab : Tab
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
        monitoringPanel.SetActive(true);
        UIEventSystem.CurrentPanel = monitoringPanel;
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        monitoringPanel.SetActive(false);
    }
}
