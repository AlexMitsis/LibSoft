using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu]
public class BookData : ScriptableObject
{
    public enum Genre
    {
        Fantasy,
        Philosophy,
        Literature,
    }

    public string title;
    public string description;
    public string author;
    public Sprite cover;
    public Genre genre;
    public int releaseDate;
    public string publisher;
}
