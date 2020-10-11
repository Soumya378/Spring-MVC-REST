package com.ranjan.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranjan.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	// Define @Post Construct to load the student data only once
	
	@PostConstruct
	public void loadData()
	{
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Soumya", "Nayak"));
		theStudents.add(new Student("Ram", "Gopal"));
		theStudents.add(new Student("Shyam", "Sundar"));
	}
	
	//define endpoint for /students
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{				
		return theStudents;
	}
	
	//define student endpoint retriveing the details by Id
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if(studentId >= theStudents.size() || studentId < 0)
		{
			throw new StudentNotFoundException("Student id Not found - "+ studentId);
		}
		return theStudents.get(studentId);
	}

}
