using System;
using UnityEngine;
using UnityEngine.UI;
using static RatingSystem;

public sealed class BorrowedBook : Book
{
    public string DateBorrowed { get; set; }
    public string DateToBeReturned { get; set; }

    public int DaysRemaining { get; set; }

    private Text dateBorrowedText;
    private Text returnDateText;
    private Text daysRemainingText;

    protected override void Start()
    {
        base.Start();
    }

    public override void GetBookData(BookData data)
    {
        if (!initializedElements)
        {
            Transform t = transform.GetChild(2);
            titleText = t.transform.GetChild(0).GetComponent<Text>();
            authorText = t.transform.GetChild(1).GetComponent<Text>();

            coverImage = transform.GetChild(1).GetComponent<Image>();
            button = transform.GetChild(3).GetComponent<Button>();

            t = transform.GetChild(4);

            dateBorrowedText = t.GetChild(0).GetComponent<Text>();
            returnDateText = t.GetChild(1).GetComponent<Text>();
            daysRemainingText = t.GetChild(2).GetComponent<Text>();

            initializedElements = true;
        }

        rating = currentRating;
        rated = false;

        if (data == null) return;

        bookData = data;
        button.onClick.AddListener(DisplayBookInfo);
        titleText.text = bookData.title;
        authorText.text = bookData.author;
        coverImage.sprite = bookData.cover;

        if (!bookSerialNumber.Equals(bookData.name)) bookSerialNumber = bookData.name;

        DateBorrowed = DateTime.UtcNow.ToLocalTime().ToString("dd/MM/yyyy | HH:mm");
        DateToBeReturned = DateTime.UtcNow.ToLocalTime().AddDays(7).ToString("dd/MM/yyyy | HH:mm");

        dateBorrowedText.text = "Date Borrowed: " + DateBorrowed;
        returnDateText.text = "Return Date: " + DateToBeReturned;

        DaysRemaining = DateTime.UtcNow.ToLocalTime().AddDays(7).Day - DateTime.UtcNow.ToLocalTime().Day;
        daysRemainingText.text = string.Empty + DaysRemaining + " days remaining.";
    }

    public void RenewTimers(string dB, string rD)
    {
        DateBorrowed = dB;
        DateToBeReturned = rD;

        dateBorrowedText.text = "Date Borrowed: " + DateBorrowed;
        returnDateText.text = "Return Date: " + DateToBeReturned;

        DaysRemaining = DateTime.UtcNow.ToLocalTime().AddDays(7).Day - DateTime.UtcNow.ToLocalTime().Day;
        daysRemainingText.text = string.Empty + DaysRemaining + " days remaining.";
    }
}
