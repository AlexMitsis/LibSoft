using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using static UIContainer;

public class OptionsTab : Tab
{
    protected override void Awake()
    {
        graphic = GetComponent<Image>();
        myButton = GetComponent<ImprovedButton>();
        myButton.Graphic = myButton.transform.GetChild(0).GetComponent<Image>();
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

    public override void OnPointerEnter()
    {
        myButton.Graphic.color = highlightColor;
    }

    public override void OnPointerExit()
    {
        myButton.Graphic.color = Color.white;
    }

    public override void OnSelect()
    {
        base.OnSelect();
        optionsPanel.SetActive(true);
        UIEventSystem.CurrentPanel = optionsPanel;
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        optionsPanel.SetActive(false);
        
    }
}
