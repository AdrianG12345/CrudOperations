package com.example.demo.controller;

import java.util.List;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Grupuri;
import com.example.demo.repository.GrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
		@Autowired
		private StudentRepository studentRepository;

		@Autowired
		private GrupRepository grupRepository;
		
		@GetMapping("/")
		public String index()
		{
			return "Welcome to a spring boot crud application?!";
		}

		///handler for putting data in DB
		@PostMapping("/saveStudent")
		public Student saveStudent(@RequestBody StudentDTO studentDTO)
		{
			int id = studentDTO.getGroupId();

			Grupuri grup = grupRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Group does not exist: "+ id));

//			/(int rollNo, String name, String address, Grupuri grupuri)
			Student newStudent = new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), grup );

			return studentRepository.save(newStudent);
			//return newStudent;
		}
		///handler for fetch all data from DB
		@GetMapping("/getAllStudent")
		public List<Student> getAll()
		{
			List<Student> studentList = studentRepository.findAll();
			return studentList;
		}
		///handler for deleting data from DB
		@DeleteMapping("/deleteStudent/{rollNo}")
		public String deleteStudent(@PathVariable int rollNo)
		{
			Student foundById = studentRepository.findById(rollNo).get();
			if (foundById != null)
			{///daca exista
				studentRepository.delete(foundById);
			}
			return "Deleted with success.";
		}
		///handler for update object
		@PutMapping("/updateStudent")
		public Student updateStudent(@RequestBody StudentDTO studentDTO)
		{
			int id = studentDTO.getGroupId();
			Grupuri grup = grupRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Group does not exist: "+ id));
			Student newStudent = new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), grup );
			Student foundById = studentRepository.findById(newStudent.getStudent_id()).get();
			if (foundById != null)
				studentRepository.save(newStudent);
			return newStudent;
		}
		///handler for fetch a single row of data
		@GetMapping("/getStudent/{rollNo}")
		public Student getStudent(@PathVariable int rollNo)
		{
			Student foundById = studentRepository.findById(rollNo).get();
			return foundById;
		}



		
		
		
}
