using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using static UIContainer;

public class BrowseTab : Tab
{
    protected override void Awake()
    {
        base.Awake();
    }

    private void Start()
    {
        browsePanel.SetActive(false);
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
        browsePanel.SetActive(true);
        searchBar.SetActive(true);
        UIEventSystem.CurrentPanel = browsePanel;

        if (UI.books.Count <= 0)
            UI.books.AddRange(browseLayoutGroup.GetComponentsInChildren<Book>());

        Member m = (Member)AccountManager.currentAccount;

        addToFavoritesButton.onClick.RemoveAllListeners();
        addToFavoritesButton.onClick.AddListener(m.AddBookToFavorites);
        addToFavoritesButton.Label.text = "ADD TO FAVORITES";

        readLaterButton.onClick.RemoveAllListeners();
        readLaterButton.onClick.AddListener(m.AddBookToReadLater);
        readLaterButton.Label.text = "READ LATER";
    }

    public override void OnDeselect()
    {
        base.OnDeselect();
        browsePanel.SetActive(false);
        searchBar.SetActive(false);
    }
}
