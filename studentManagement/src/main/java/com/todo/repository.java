package com.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.models.Student;

@Repository
public interface repository extends JpaRepository<Student, Long> {

	

	
}
