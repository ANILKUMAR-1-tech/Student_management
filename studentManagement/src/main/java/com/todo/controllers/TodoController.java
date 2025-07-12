package com.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.todo.models.Student;
import com.todo.services.TodoService;

@Controller
@RequestMapping("/students")
public class TodoController {

	@Autowired
	public TodoService service;
	
	@GetMapping("/")
	public String viewStudentsPage(@RequestParam(defaultValue = "0") int page,
	                               @RequestParam(defaultValue = "5") int size,
	                               @RequestParam(defaultValue = "name") String sortField,
	                               @RequestParam(defaultValue = "asc") String sortDir,
	                               Model model) {

	    org.springframework.data.domain.Page<Student> studentPage = service.getPaginatedStudents(page, size, sortField, sortDir);

	    model.addAttribute("students", studentPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", studentPage.getTotalPages());
	    model.addAttribute("totalItems", studentPage.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    return "students";
	}

	
	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("student", new Student());
		return "add";
	}
	@PostMapping("/add")
	public String saveStudent(@ModelAttribute Student student) {
	    service.saveStudent(student); 
	    return "redirect:/students/"; 
	}
	
	
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student) {
        service.updateStudent(student);
        return "redirect:/students/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudentById(id);
        return "redirect:/students/";
    }
}

