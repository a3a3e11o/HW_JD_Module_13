package org.example.controller;

import org.example.data.entity.Note;
import org.example.data.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteRepository.listAll());
        return "note/list";
    }

    @GetMapping("/add")
    public String showAddNoteForm() {
        return "note/add";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute Note note) {
        noteRepository.add(note);
        return "redirect:/note/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") long id) {
        noteRepository.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNote(@RequestParam("id") long id, Model model) {
        Note note = noteRepository.getById(id);
        model.addAttribute("note", note);
        return "note/edit";
    }

    @PostMapping("/edit")
    public String saveEditedNote(@ModelAttribute Note note) {
        noteRepository.update(note);
        return "redirect:/note/list";
    }
}