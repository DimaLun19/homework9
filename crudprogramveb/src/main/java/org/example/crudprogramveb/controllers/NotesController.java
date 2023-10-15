package org.example.crudprogramveb.controllers;

import org.example.crudprogramveb.models.WebNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.crudprogramveb.dao.WebNoteDAO;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final WebNoteDAO webNoteDAO; // создаём WebNoteDAO

    @Autowired
    public NotesController(WebNoteDAO webNoteDAO) { // создаём конструктор
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping()
    public String index(Model model) { // метод, получающий все заметки из DAO и передающий их на веб-страницу
        model.addAttribute("notes", webNoteDAO.index());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { // метод, получающий одну заметку по id из DAO и передающий её на веб-страницу
        model.addAttribute("note", webNoteDAO.show(id));
        return "notes/show";
    }

    @GetMapping("/new") // метод, представляющий веб-страницу с формой
    public String newNote(Model model) {
        model.addAttribute("webNote", new WebNote());
        return "notes/new";
    }

    @PostMapping() // метод, отвечающий за сохранение переданного из формы объекта
    public String create(@ModelAttribute("webNote") WebNote webNote) {
        webNoteDAO.save(webNote);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit") // метод, предоставляющий страницу с формой для редактирования заметки
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("webNote", webNoteDAO.show(id));
        return "notes/edit";
    }

    @PatchMapping("/{id}") // метод для нашего PATCH-запроса
    public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {
        webNoteDAO.update(id, webNote);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}") // метод, реализующий удаление заметки
    public String delete(@PathVariable("id") int id) {
        webNoteDAO.delete(id);
        return "redirect:/notes";
    }
}
