package com.spring.batchtest.batch.controller;


import com.spring.batchtest.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/std")
public class StudentController {
    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudentList(){
        var list = Arrays.asList(new Student(1L,"kia","shama"),
                new Student(2L,"kia1","shama1"),
                new Student(3L,"kia2","shama2"),
                new Student(4L,"kia3","shama3")
                );
        return  ResponseEntity.ok(list);

    }
}
