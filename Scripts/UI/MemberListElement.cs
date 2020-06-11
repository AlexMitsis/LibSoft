using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using static UIContainer;

public class MemberListElement : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler
{
    Text t = null;

    private Text usernameText;
    private bool initialized = false;
    private Button button;

    public void GetElementInfo(string name)
    {
        if (!initialized)
        {
            usernameText = transform.GetChild(1).GetComponent<Text>();
            button = transform.GetChild(2).GetComponent<Button>();
            t = Resources.Load("TextObject", typeof(Text)) as Text;

            button.onClick.AddListener(DisplayBorrowedBooks);
            initialized = true;
        }

        usernameText.text = name;
    }

    private void ChangeColor(Color c)
    {
        usernameText.color = c;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {
        ChangeColor(Color.magenta);
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        ChangeColor(Color.white);
    }

    public void DisplayBorrowedBooks()
    {
        for (int i = 0; i < monitoring_BorrowedBooks.transform.childCount; i++)
        {
            GameObject child = monitoring_BorrowedBooks.transform.GetChild(i).gameObject;

            Destroy(child);
        }

        for (int i = 0; i < AccountManager.accounts.Count; i++)
        {
            if (AccountManager.accounts[i].username.Equals(usernameText.text))
            {
                Member m = (Member)AccountManager.accounts[i];

                if(m.borrowedBooks.Count <= 0) m.LoadSavedData();

                for (int j = 0; j < m.borrowedBooks.Count; j++)
                {
                    Text newEntry = Instantiate(t, monitoring_BorrowedBooks.transform);

                    newEntry.text = m.borrowedBooks[j].bookData.title + " [" + m.borrowedBooks[j].bookSerialNumber + "]";
                }

                break;
            }
        }
    }
}
