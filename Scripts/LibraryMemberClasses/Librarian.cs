using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using static UIContainer;

public sealed class Librarian : LibraryMember
{
    public static Librarian Instance { get; set; }

    private void Awake()
    {
        Singleton();
    }

    private void Singleton()
    {
        if (Instance != this && Instance != null)
            Destroy(gameObject);
        else Instance = this;
    }

    public static void PostNewsArticle(string header, string article)
    {
        NewsUpdateTemplate template = Instantiate(Resources.Load("NewsUpdateTemplate", typeof(NewsUpdateTemplate)), newsLayoutGroup.transform) as NewsUpdateTemplate;
        headerInputField.text = string.Empty;
        articleInputField.text = string.Empty;

        template.GetUpdateInfo(header, article);
    }
}
