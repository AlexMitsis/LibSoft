using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class BookEventHandler : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler
{
    private Book book;
    //[SerializeField] private Color selectedColor = Color.clear;

    private void OnEnable()
    {
        ColorLetters(Color.white);
    }

    private void Awake()
    {
        book = GetComponent<Book>();
    }

    private void ColorLetters(Color color)
    {
        if (book && book.titleText) book.titleText.color = color;
        if (book && book.authorText) book.authorText.color = color;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {
        ColorLetters(Color.magenta);
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        ColorLetters(Color.white);
    }
}
