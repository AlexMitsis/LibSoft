using System;
using System.Collections.Generic;
using UnityEngine;
using static UIContainer;

public class Member : LibraryMember
{
    public List<Book> borrowedBooks = new List<Book>();
    public List<Book> favoriteBooks = new List<Book>();
    public List<Book> toReadBooks = new List<Book>();

    public List<string> readNotifications = new List<string>();

    public void BorrowBook()
    {
        BorrowedBook bookTemplate = Instantiate(Resources.Load("BorrowedBookTemplate", typeof(BorrowedBook)),
        personalLibraryLayoutGroup.transform) as BorrowedBook;

        bookTemplate.GetBookData(UIEventSystem.selectedBook.bookData);
        bookTemplate.rating = UIEventSystem.selectedBook.rating;
        borrowedBooks.Add(bookTemplate);

        borrowButton.onClick.RemoveAllListeners();
        borrowButton.onClick.AddListener(RenewBook);
        borrowButton.Label.text = "RENEW";
    }

    public void AddBookToFavorites()
    {
        bool existsInCollection = false;

        for (int i = 0; i < favoriteBooks.Count; i++)
        {
            if (favoriteBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                existsInCollection = true;
                break;
            }
        }

        if (existsInCollection)
        {
            UIEventSystem.Instance.StartCoroutine(UIEventSystem.ActionDeniedNotification("This book is already in your Favorites!"));
            return;
        }

        Book bookTemplate = Instantiate(Resources.Load("BookTemplate", typeof(Book)),
        favoritesLayoutGroup.transform) as Book;

        bookTemplate.GetBookData(UIEventSystem.selectedBook.bookData);
        bookTemplate.rating = UIEventSystem.selectedBook.rating;
        favoriteBooks.Add(bookTemplate);
    }

    public void AddBookToReadLater()
    {
        bool existsInCollection = false;

        for (int i = 0; i < toReadBooks.Count; i++)
        {
            if (toReadBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                existsInCollection = true;
                break;
            }
        }

        if (existsInCollection)
        {
            UIEventSystem.Instance.StartCoroutine(UIEventSystem.ActionDeniedNotification("This book is already in your To Read List!"));
            return;
        }

        Book bookTemplate = Instantiate(Resources.Load("BookTemplate", typeof(Book)),
        toReadLayoutGroup.transform) as Book;

        bookTemplate.GetBookData(UIEventSystem.selectedBook.bookData);
        bookTemplate.rating = UIEventSystem.selectedBook.rating;
        toReadBooks.Add(bookTemplate);
    }

    public void RemoveBookFromCollection(List<Book> collection, BookData data)
    {
        Book bookToRemove = null;

        for (int i = 0; i < collection.Count; i++)
        {
            if (collection[i].bookData.Equals(data))
            {
                bookToRemove = collection[i];
                break;
            }
        }

        if (!bookToRemove) return;

        collection.Remove(bookToRemove);
        Destroy(bookToRemove.gameObject);
    }

    public void RenewBook()
    {
        for (int i = 0; i < borrowedBooks.Count; i++)
        {
            if (borrowedBooks[i].bookData.Equals(UIEventSystem.selectedBook.bookData))
            {
                BorrowedBook book = (BorrowedBook)borrowedBooks[i];

                book.RenewTimers(DateTime.UtcNow.ToLocalTime().ToString("dd/MM/yyyy | HH:mm"),
                    DateTime.UtcNow.ToLocalTime().AddDays(7).ToString("dd/MM/yyyy | HH:mm"));

                break;
            }
        }
    }

    public void ClearAllCollections()
    {
        borrowedBooks.Clear();
        favoriteBooks.Clear();
        toReadBooks.Clear();
    }

    public void LoadSavedData()
    {
        SavedMemberData data = SavingSystem.LoadMemberData(this);

        if (data != null)
        {
            for (int i = 0; i < data.bookIDs_Borrowed.Count; i++)
            {
                BorrowedBook bookTemplate = Instantiate(Resources.Load("BorrowedBookTemplate", typeof(BorrowedBook)),
                personalLibraryLayoutGroup.transform) as BorrowedBook;

                bookTemplate.GetBookData(Resources.Load("Books/" + data.bookIDs_Borrowed[i], typeof(BookData)) as BookData);
                borrowedBooks.Add(bookTemplate);

                bookTemplate.rating = data.borrowedBookRatings[i];
                bookTemplate.DateBorrowed = data.borrowedBooks_BorrowedDates[i];
                bookTemplate.DateToBeReturned = data.borrowedBooks_ReturnDates[i];
            }

            for (int i = 0; i < data.bookIDs_Favorites.Count; i++)
            {
                Book bookTemplate = Instantiate(Resources.Load("BookTemplate", typeof(Book)),
                favoritesLayoutGroup.transform) as Book;

                bookTemplate.GetBookData(Resources.Load("Books/" + data.bookIDs_Favorites[i], typeof(BookData)) as BookData);
                bookTemplate.rating = data.favoriteBookRatings[i];
                favoriteBooks.Add(bookTemplate);
            }

            for (int i = 0; i < data.bookIDs_ToRead.Count; i++)
            {
                Book bookTemplate = Instantiate(Resources.Load("BookTemplate", typeof(Book)),
                toReadLayoutGroup.transform) as Book;

                bookTemplate.GetBookData(Resources.Load("Books/" + data.bookIDs_ToRead[i], typeof(BookData)) as BookData);
                bookTemplate.rating = data.toReadBookRatings[i];
                toReadBooks.Add(bookTemplate);
            }
        }
    }
}
