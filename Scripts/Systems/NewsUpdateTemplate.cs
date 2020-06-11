using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;
using static UIContainer;

public class NewsUpdateTemplate : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler
{
    private Text headerText;
    private Text articleText;
    private Button button;

    private string realArticle;

    private bool initializedElements = false;

    private void OnDisable()
    {
        ColorLetters(Color.white);
    }

    public void GetUpdateInfo(string header, string article)
    {
        if (!initializedElements)
        {
            headerText = transform.GetChild(0).GetComponent<Text>();
            articleText = transform.GetChild(1).GetComponent<Text>();
            button = transform.GetChild(2).GetComponent<Button>();

            button.onClick.AddListener(DisplayNewsArticle);

            initializedElements = true;
        }

        headerText.text = header;

        realArticle = article;

        if (article.Length >= 50)
        {
            char[] first50Chars = new char[50];

            for (int i = 0; i < 50; i++) first50Chars[i] = article[i];

            articleText.text = first50Chars.ToString() + "...";
        }
        else articleText.text = article;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {
        ColorLetters(Color.magenta);
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        ColorLetters(Color.white);
    }

    private void ColorLetters(Color c)
    {
        headerText.color = c;
        articleText.color = c;
    }

    private void DisplayNewsArticle()
    {
        newsPanel.SetActive(false);
        newsPagePanel.SetActive(true);

        newsPagePanel_Header.text = headerText.text;
        newsPagePanel_ArticleText.text = realArticle;
    }
}
