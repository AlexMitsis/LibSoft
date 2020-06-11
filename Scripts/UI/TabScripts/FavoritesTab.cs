using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIContainer;

public class FavoritesTab : Tab
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
        favoritesPanel.SetActive(true);
        UIEventSystem.CurrentPanel = favoritesPanel;

        addToFavoritesButton.Label.text = "REMOVE FROM FAVORITES";
        addToFavoritesButton.onClick.RemoveAllListeners();

        Member m = (Member)AccountManager.currentAccount;

        addToFavoritesButton.onClick.AddListener(delegate { m.RemoveBookFromCollection
            (m.favoriteBooks, UIEventSystem.selectedBook.bookData); });
        addToFavoritesButton.onClick.AddListener(UIEventSystem.BackToPreviousPanel);

        readLaterButton.onClick.RemoveAllListeners();
        readLaterButton.onClick.AddListener(m.AddBookToReadLater);
        readLaterButton.Label.text = "READ LATER";
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        favoritesPanel.SetActive(false);
    }
}
