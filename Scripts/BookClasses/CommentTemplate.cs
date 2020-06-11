using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CommentTemplate : MonoBehaviour
{
    private Text usernameText;
    private Text commentText;

    private bool initializedElements = false;

    private void Start()
    {
        if (!initializedElements)
        {
            Transform t = transform.Find("CommentInfoHolder").transform;

            usernameText = t.GetChild(0).GetComponent<Text>();
            commentText = t.GetChild(1).GetComponent<Text>();
        }
    }

    public void GetCommentData(Book.Comment commentInfo)
    {
        if (!initializedElements)
        {
            Transform t = transform.Find("CommentInfoHolder").transform;

            usernameText = t.GetChild(0).GetComponent<Text>();
            commentText = t.GetChild(1).GetComponent<Text>();
        }

        string ratingInfo;

        if (commentInfo.rating == 0) ratingInfo = "(No rating)";
        else ratingInfo = " (" + commentInfo.rating + "/5 stars)";

        usernameText.text = "By " + commentInfo.commenterName + " " + ratingInfo;
        commentText.text = commentInfo.comment;
    }
}
