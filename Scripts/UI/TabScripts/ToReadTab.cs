using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using static UIContainer;

public class ToReadTab : Tab
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
        toReadPanel.SetActive(true);
        UIEventSystem.CurrentPanel = toReadPanel;
        readLaterButton.Label.text = "REMOVE";
        readLaterButton.onClick.RemoveAllListeners();

        Member m = (Member)AccountManager.currentAccount;

        readLaterButton.onClick.AddListener(delegate { m.RemoveBookFromCollection
            (m.toReadBooks, UIEventSystem.selectedBook.bookData); });
        readLaterButton.onClick.AddListener(UIEventSystem.BackToPreviousPanel);

        addToFavoritesButton.onClick.RemoveAllListeners();
        addToFavoritesButton.onClick.AddListener(m.AddBookToFavorites);
        addToFavoritesButton.Label.text = "ADD TO FAVORITES";
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        toReadPanel.SetActive(false);
    }
}
