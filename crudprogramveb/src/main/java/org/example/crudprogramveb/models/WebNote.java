package org.example.crudprogramveb.models;

public class WebNote { // класс - модель данных для заметок
    private int id;  // создаём id
    private String note;  // создаём String

    public WebNote(int id, String note) { // создаём конструктор
        this.id = id;
        this.note = note;
    }

    public WebNote() { // создаём пустой конструктор

    }

    public int getId() { // создаём геттеры и сеттеры для id и note
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
