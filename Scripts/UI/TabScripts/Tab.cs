using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using static UIContainer;

[RequireComponent(typeof(ImprovedButton))]
[RequireComponent(typeof(EventTrigger))]
public abstract class Tab : MonoBehaviour
{
    protected Image graphic;
    [SerializeField] protected Color defaultColor = Color.clear;
    [SerializeField] protected Color highlightColor = Color.clear;
    [SerializeField] protected Color selectedColor = Color.clear;
    protected ImprovedButton myButton;

    public virtual void OnPointerEnter()
    {
        myButton.Label.color = highlightColor;
    }

    public virtual void OnPointerExit()
    {
        myButton.Label.color = Color.white;
    }

    public virtual void OnSelect()
    {
        graphic.color = selectedColor;

        if (bookPagePanel.activeSelf) bookPagePanel.SetActive(false);
        if (newsPagePanel.activeSelf) newsPagePanel.SetActive(false);
    }

    public virtual void OnDeselect()
    {
        graphic.color = defaultColor;

        if (bookPagePanel.activeSelf) bookPagePanel.SetActive(false);
        if (newsPagePanel.activeSelf) newsPagePanel.SetActive(false);
    }

    protected virtual void Awake()
    {
        graphic = GetComponent<Image>();
        myButton = GetComponent<ImprovedButton>();
        myButton.Label = myButton.transform.GetChild(0).GetComponent<Text>();
        myButton.onClick.AddListener(delegate { UIEventSystem.SelectNewTab(this); });

        EventTrigger trigger = GetComponent<EventTrigger>();

        EventTrigger.Entry entry1 = new EventTrigger.Entry();
        entry1.eventID = EventTriggerType.PointerEnter;
        entry1.callback.AddListener(delegate { OnPointerEnter(); });
        trigger.triggers.Add(entry1);

        EventTrigger.Entry entry2 = new EventTrigger.Entry();
        entry2.eventID = EventTriggerType.PointerExit;
        entry2.callback.AddListener(delegate { OnPointerExit(); });
        trigger.triggers.Add(entry2);

        graphic.color = defaultColor;
    }
}
