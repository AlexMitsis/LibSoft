using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIContainer;
using static RatingSystem;

public class Book : MonoBehaviour
{
    public class Comment
    {
        public string commenterName;
        public string comment;
        public int rating;

        public Comment(string cN, string c, int r)
        {
            commenterName = cN;
            comment = c;
            rating = r;
        }
    }

    public string bookSerialNumber = string.Empty;

    public Text titleText;
    public Text authorText;
    protected Image coverImage;
    protected Button button;

    public BookData bookData;
    [Range(0, 5)] public int rating = 0;
    public bool rated = false;

    public List<Comment> comments = new List<Comment>();

    protected bool initializedElements = false;

    protected virtual void OnDisable()
    {
        if (bookData == null && !bookSerialNumber.Equals(string.Empty))
            GetBookData(Resources.Load("Books/" + bookSerialNumber, typeof(BookData)) as BookData);
    }

    protected virtual void Start()
    {
        if (bookData == null && !bookSerialNumber.Equals(string.Empty))
            GetBookData(Resources.Load("Books/" + bookSerialNumber, typeof(BookData)) as BookData);
    }

    public virtual void GetBookData(BookData data)
    {
        if (!initializedElements)
        {
            Transform t = transform.GetChild(1);
            titleText = t.transform.GetChild(0).GetComponent<Text>();
            authorText = t.transform.GetChild(1).GetComponent<Text>();

            coverImage = transform.GetChild(0).GetComponent<Image>();
            button = transform.GetChild(2).GetComponent<Button>();

            initializedElements = true;
        }

        rating = currentRating;
        rated = false;

        if (data == null) return;

        bookData = data;
        button.onClick.RemoveAllListeners();
        button.onClick.AddListener(DisplayBookInfo);
        titleText.text = bookData.title;
        authorText.text = bookData.author;
        coverImage.sprite = bookData.cover;

        if (!bookSerialNumber.Equals(bookData.name)) bookSerialNumber = bookData.name;
    }

    protected void DisplayBookInfo()
    {
        if (!bookData) return;

        bookPagePanel.SetActive(true);
        UIEventSystem.CurrentPanel.SetActive(false);
        UIEventSystem.selectedBook = this;
        bookPage_Title.text = bookData.title + " (" + bookData.releaseDate + "), " + "by " + bookData.author;
        bookPage_Description.text = bookData.description;
        bookPage_Cover.sprite = bookData.cover;

        Member m = (Member)AccountManager.currentAccount;

        GetBookRating(m);
        GetBookComments(m);
        searchBar.SetActive(false);

        bool alreadyBorrowed = false;

        for (int i = 0; i < m.borrowedBooks.Count; i++)
        {
            if (m.borrowedBooks[i].bookData.Equals(bookData))
            {
                alreadyBorrowed = true;
                break;
            }
        }

        if (!alreadyBorrowed)
        {
            borrowButton.onClick.RemoveAllListeners();
            borrowButton.onClick.AddListener(m.BorrowBook);
            borrowButton.Label.text = "BORROW";
        }
        else
        {
            borrowButton.onClick.RemoveAllListeners();
            borrowButton.onClick.AddListener(m.RenewBook);
            borrowButton.Label.text = "RENEW";
        }
    }

    protected void GetBookComments(Member m)
    {
        for (int i = 0; i < commentsLayoutGroup.transform.childCount; i++)
        {
            GameObject comment = commentsLayoutGroup.transform.GetChild(i).gameObject;

            Destroy(comment);
        }

        List<Comment> commentsOfOtherCollection = new List<Comment>();
        commentsOfOtherCollection.Clear();

        if (commentsOfOtherCollection.Count == 0)
        {
            for (int i = 0; i < m.borrowedBooks.Count; i++)
            {
                if (m.borrowedBooks[i].comments.Count > 0 && m.borrowedBooks[i].bookData.Equals(bookData))
                {
                    commentsOfOtherCollection.AddRange(m.borrowedBooks[i].comments);
                    break;
                }
            }
        }

        if (commentsOfOtherCollection.Count == 0)
        {
            for (int i = 0; i < m.favoriteBooks.Count; i++)
            {
                if (m.favoriteBooks[i].comments.Count > 0 && m.favoriteBooks[i].bookData.Equals(bookData))
                {
                    commentsOfOtherCollection.AddRange(m.favoriteBooks[i].comments);
                    break;
                }
            }
        }

        if (commentsOfOtherCollection.Count == 0)
        {
            for (int i = 0; i < m.toReadBooks.Count; i++)
            {
                if (m.toReadBooks[i].comments.Count > 0 && m.toReadBooks[i].bookData.Equals(bookData))
                {
                    commentsOfOtherCollection.AddRange(m.toReadBooks[i].comments);
                    break;
                }
            }
        }

        if (commentsOfOtherCollection.Count == 0)
        {
            for (int i = 0; i < browseLayoutGroup.transform.childCount; i++)
            {
                Book b = browseLayoutGroup.transform.GetChild(i).GetComponent<Book>();

                if (b.comments.Count > 0 && b.bookData.Equals(bookData))
                {
                    commentsOfOtherCollection.AddRange(b.comments);
                    break;
                }
            }
        }

        if (commentsOfOtherCollection.Count > 0)
            comments.Clear();

        if (commentsOfOtherCollection.Count > 0)
            comments.AddRange(commentsOfOtherCollection);
        

        for (int i = 0; i < comments.Count; i++)
        {
            Comment c = comments[i];

            CommentTemplate commentTemplate = Instantiate(Resources.Load("BookCommentTemplate", typeof(CommentTemplate)), commentsLayoutGroup.transform) as CommentTemplate;
            commentTemplate.GetCommentData(new Comment(c.commenterName, c.comment, c.rating));
        }
    }

    protected void GetBookRating(Member m)
    {
        int ratingOfOtherCollection = 0;

        if (ratingOfOtherCollection == 0)
        {
            for (int i = 0; i < browseLayoutGroup.transform.childCount; i++)
            {
                Book book = browseLayoutGroup.transform.GetChild(i).GetComponent<Book>();

                if (!book || book.bookData == null) continue;

                if (book.bookData.Equals(bookData)
                    && book.rating > 0)
                {
                    ratingOfOtherCollection = book.rating;
                    break;
                }
            }
        }

        if (ratingOfOtherCollection == 0)
        {
            for (int i = 0; i < m.borrowedBooks.Count; i++)
            {
                if (m.borrowedBooks[i].bookData.Equals(bookData)
                    && m.borrowedBooks[i].rating > 0)
                {
                    ratingOfOtherCollection = m.borrowedBooks[i].rating;
                    break;
                }
            }
        }

        if (ratingOfOtherCollection == 0)
        {
            for (int i = 0; i < m.favoriteBooks.Count; i++)
            {
                if (m.favoriteBooks[i].bookData.Equals(bookData)
                    && m.favoriteBooks[i].rating > 0)
                {
                    ratingOfOtherCollection = m.favoriteBooks[i].rating;
                    break;
                }
            }
        }

        if (ratingOfOtherCollection == 0)
        {
            for (int i = 0; i < m.toReadBooks.Count; i++)
            {
                if (m.toReadBooks[i].bookData.Equals(bookData)
                    && m.toReadBooks[i].rating > 0)
                {
                    ratingOfOtherCollection = m.toReadBooks[i].rating;
                    break;
                }
            }
        }

        if (ratingOfOtherCollection > 0)
            rating = ratingOfOtherCollection;

        if (rating > 0)
            for (int i = 0; i < rating; i++) stars[i].image.color = Color.magenta;
        else
            for (int i = 0; i < stars.Length; i++) stars[i].image.color = stars[i].defaultColor;
    }
}
