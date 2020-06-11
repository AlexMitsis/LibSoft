using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;
using static RatingSystem;

public class RatingStar : MonoBehaviour
{
    public Color defaultColor;

    public Image image;
    private EventTrigger eventTrigger;
    [SerializeField] private int index = 0;

    private void Awake()
    {
        image = GetComponent<Image>();
        eventTrigger = GetComponent<EventTrigger>();

        EventTrigger.Entry entry = new EventTrigger.Entry();
        entry.eventID = EventTriggerType.PointerEnter;

        EventTrigger.Entry entry1 = new EventTrigger.Entry();
        entry1.eventID = EventTriggerType.PointerExit;

        EventTrigger.Entry entry2 = new EventTrigger.Entry();
        entry2.eventID = EventTriggerType.PointerClick;

        entry.callback.AddListener(delegate { OnHoverEnter(); });
        eventTrigger.triggers.Add(entry);

        entry1.callback.AddListener(delegate { OnHoverExit(); });
        eventTrigger.triggers.Add(entry1);

        entry2.callback.AddListener(delegate { OnClick(); });
        eventTrigger.triggers.Add(entry2);

        stars[index] = this;
        defaultColor = image.color;
    }

    private void OnHoverEnter()
    {
        for (int i = 0; i < stars.Length; i++)
        {
            stars[i].image.color = defaultColor;
        }

        for (int i = 0; i < stars.Length; i++)
        {
            if (i <= transform.GetSiblingIndex())
            {
                stars[i].image.color = Color.magenta;
            }
        }
    }

    private void OnHoverExit()
    {
        if (!UIEventSystem.selectedBook.rated)
        {
            for (int i = 0; i < stars.Length; i++)
            {
                if (i <= transform.GetSiblingIndex())
                {
                    stars[i].image.color = defaultColor;
                }
            }
        }
        UIEventSystem.selectedBook.rated = false;

        if (UIEventSystem.selectedBook.rating > 0)
        {
            for (int i = 0; i < stars.Length; i++)
            {
                if (i < UIEventSystem.selectedBook.rating)
                {
                    stars[i].image.color = Color.magenta;
                }
            }
        }
    }

    private void OnClick()
    {
        RateBook(transform.GetSiblingIndex() + 1, transform);

        for (int i = 0; i < stars.Length; i++)
        {
            if (i <= transform.GetSiblingIndex())
            {
                stars[i].image.color = Color.magenta;
            }
            else stars[i].image.color = defaultColor;
        }
    }
}
