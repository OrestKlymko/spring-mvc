package com.example.controller;


import com.example.crudCommand.NoteService;
import com.example.entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class TestController {
	private NoteService noteService = new NoteService(); // видалю як буде підключено бд, поки для дебагу

	@GetMapping("/list")
	public ModelAndView getAllNotes() {
		ModelAndView modelAndView = new ModelAndView("test");
		modelAndView.addObject("listNotes", noteService.listAll());
		return modelAndView;
	}

	@GetMapping("/edit")
	public ModelAndView editNote(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("edit-note");
		modelAndView.addObject("noteEdit", noteService.getById(Long.parseLong(id)));
		return modelAndView;
	}

	@PostMapping("/edit")
	public ModelAndView completeEditNote(@RequestParam String id, String title, String content) {
		Note byId = noteService.getById(Long.parseLong(id));
		byId.setTitle(title);
		byId.setContent(content);
		noteService.update(byId);
		return new ModelAndView("redirect:/note/list");
	}

	@PostMapping("/delete")
	public ModelAndView deleteNote(@RequestParam String id) {
		noteService.deleteById(Long.parseLong(id));
		return new ModelAndView("redirect:/note/list");
	}
}
