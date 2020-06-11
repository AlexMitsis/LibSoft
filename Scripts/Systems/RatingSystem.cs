using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;
using static UIContainer;

public class RatingSystem : MonoBehaviour
{
    public static RatingStar[] stars = new RatingStar[5];
    public static int currentRating;

    public static void RateBook(int value, Transform t)
    {
        UIEventSystem.selectedBook.rating = value;
        currentRating = UIEventSystem.selectedBook.rating;
        UIEventSystem.selectedBook.rated = true;

        Member m = (Member)AccountManager.currentAccount;

        for (int i = 0; i < browseLayoutGroup.transform.childCount; i++)
        {
            Book b = browseLayoutGroup.transform.GetChild(i).GetComponent<Book>();

            if (!b || b.bookData == null) continue;

            if (b.bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                b.rating = value;
                break;
            }
        }

        for (int i = 0; i < m.borrowedBooks.Count; i++)
        {
            if (m.borrowedBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                m.borrowedBooks[i].rating = value;
                break;
            }
        }

        for (int i = 0; i < m.favoriteBooks.Count; i++)
        {
            if (m.favoriteBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                m.favoriteBooks[i].rating = value;
                break;
            }
        }

        for (int i = 0; i < m.toReadBooks.Count; i++)
        {
            if (m.toReadBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                m.toReadBooks[i].rating = value;
                break;
            }
        }
    }
}
