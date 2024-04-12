package com.example.demo.service;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Grupuri;
import com.example.demo.entity.Student;
import com.example.demo.repository.GrupRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GrupRepository grupRepository;

    ///handler for putting data in DB
    public Student saveStudent(StudentDTO studentDTO)
    {
        int id = studentDTO.getGroupId();

        Grupuri grup = grupRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Group does not exist: "+ id));
        Student newStudent = new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), grup );

        return studentRepository.save(newStudent);
    }
    ///handler for fetch all data from DB
    public List<Student> getAll()
    {
        return studentRepository.findAll();
    }
    ///handler for deleting data from DB
    public String deleteStudent(int rollNo)
    {
        Student foundById = studentRepository.findById(rollNo).get();
        if (foundById != null)
        {///daca exista
            studentRepository.delete(foundById);
        }
        return "Deleted with success.";
    }
    ///handler for update object
    public Student updateStudent(StudentDTO studentDTO)
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
    public Student getStudent(int rollNo)
    {
        Student foundById = studentRepository.findById(rollNo).get();
        return foundById;
    }


}
