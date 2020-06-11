using System.Collections.Generic;
using System;
using static UIContainer;
using JetBrains.Annotations;

[Serializable]
public class SavedSystemData
{
    public List<string> savedUsernames = new List<string>();
    public List<string> savedPasswords = new List<string>();

    public SavedSystemData()
    {
        SaveUser();
    }

    private void SaveUser()
    {
        for (int i = 0; i < AccountManager.accounts.Count; i++)
        {
            if (AccountManager.accounts[i] is Librarian) continue;

            savedUsernames.Add(AccountManager.accounts[i].username);
            savedPasswords.Add(AccountManager.accounts[i].password);
        }
    }
}

[Serializable]
public class SavedMemberData
{
    public List<string> bookIDs_Borrowed = new List<string>();
    public List<string> borrowedBooks_BorrowedDates = new List<string>();
    public List<string> borrowedBooks_ReturnDates = new List<string>();
    public List<int> borrowedBookRatings = new List<int>();

    public List<string> bookIDs_Favorites = new List<string>();
    public List<int> favoriteBookRatings = new List<int>();

    public List<string> bookIDs_ToRead = new List<string>();
    public List<int> toReadBookRatings = new List<int>();

    public SavedMemberData()
    {
        SaveData();
    }

    private void SaveData()
    {
        if (AccountManager.currentAccount is Librarian) return;

        Member m = (Member)AccountManager.currentAccount;

        SaveBorrowedBooks();
        SaveBooks(bookIDs_Favorites, m.favoriteBooks);
        SaveBooks(bookIDs_ToRead, m.toReadBooks);

        SaveBookRatings(borrowedBookRatings, m.borrowedBooks);
        SaveBookRatings(favoriteBookRatings, m.favoriteBooks);
        SaveBookRatings(toReadBookRatings, m.toReadBooks);
    }

    private void SaveBorrowedBooks()
    {
        Member m = (Member)AccountManager.currentAccount;

        for (int i = 0; i < m.borrowedBooks.Count; i++)
        {
            bookIDs_Borrowed.Add(m.borrowedBooks[i].bookSerialNumber);

            BorrowedBook thisBook = (BorrowedBook)m.borrowedBooks[i];

            borrowedBooks_BorrowedDates.Add(thisBook.DateBorrowed);
            borrowedBooks_ReturnDates.Add(thisBook.DateToBeReturned);
        }
    }

    private void SaveBooks(List<string> collectionToStoreTo, List<Book> collectionToSave)
    {
        for (int i = 0; i < collectionToSave.Count; i++)
        {
            collectionToStoreTo.Add(collectionToSave[i].bookSerialNumber);
        }
    }

    private void SaveBookRatings(List<int> collectionToStoreTo, List<Book> collectionToSave)
    {
        for (int i = 0; i < collectionToSave.Count; i++)
        {
            collectionToStoreTo.Add(collectionToSave[i].rating);
        }
    }
}
