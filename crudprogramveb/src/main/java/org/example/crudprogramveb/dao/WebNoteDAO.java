package org.example.crudprogramveb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import org.example.crudprogramveb.models.WebNote;

@Component
public class WebNoteDAO { // DAO-класс для модели
    private static int NOTES_COUNT; // создаём int
    private List<WebNote> notes; //создаём List<WebNote>

    public WebNoteDAO() { // создаём конструктор
        notes = new ArrayList<WebNote>(); // создаём ArrayList<WebNote>() и добавляем новые объекты этого класса

        notes.add(new WebNote(++NOTES_COUNT, "test1"));
        notes.add(new WebNote(++NOTES_COUNT, "test2"));
        notes.add(new WebNote(++NOTES_COUNT, "test3"));
        notes.add(new WebNote(++NOTES_COUNT, "test4"));
        notes.add(new WebNote(++NOTES_COUNT, "test5"));
    }

    public List<WebNote> index() { // метод, возвращающий List<WebNote>
        return notes;
    }

    public WebNote show(int id) { // метод, возвращающий конкретную заметку по id
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    public void save(WebNote webNote) { // метод, сохраняющий новые заметки
        webNote.setId(++NOTES_COUNT);
        notes.add(webNote);
    }

    public void update(int id, WebNote updatedNote) { //метод для обновления данных
        WebNote toUpdateNote = show(id);
        toUpdateNote.setNote(updatedNote.getNote());
    }

    public void delete(int id) { // метод для удаления заметки
        notes.removeIf(n -> n.getId() == id);
    }
}
