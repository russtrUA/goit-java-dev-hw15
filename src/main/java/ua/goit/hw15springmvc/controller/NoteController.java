package ua.goit.hw15springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.hw15springmvc.model.Note;
import ua.goit.hw15springmvc.service.NoteService;


@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView findAllNotes() {
        ModelAndView modelAndView = new ModelAndView("note/list");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteNoteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("note/edit");
        modelAndView.addObject("note", noteService.getById(id));
        return modelAndView;
    }
    @PostMapping(value ="/edit")
    public ModelAndView editNoteById(@ModelAttribute Note note) {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }
}
