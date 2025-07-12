package com.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todo.repository;
import com.todo.models.Student;

@Service
public class TodoService {

    @Autowired
    private repository repo;

    public Page<Student> getPaginatedStudents(int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

	public void saveStudent(Student student) {
		repo.save(student);
		
	}

	public Student getStudentById(Long id) {
	    return repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public void updateStudent(Student student) {
	   repo.save(student);
	}

	public void deleteStudentById(Long id) {
	   repo.deleteById(id);
	}
}
