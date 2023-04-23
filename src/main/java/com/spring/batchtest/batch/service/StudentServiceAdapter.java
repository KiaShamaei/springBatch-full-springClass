package com.spring.batchtest.batch.service;


import com.spring.batchtest.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceAdapter {
    List<Student> list;

    public Student restCallStudent(){
        System.out.println("rest student call ---");
        RestTemplate r = new RestTemplate();
       var respones= r.getForObject("http://localhost:8080/std/list" , Student[].class);
       list = new ArrayList<>();
       Arrays.stream(respones).forEach(list::add);
       return list.remove(0);
    }
    public Student getStudent(){
        System.out.println("get student call ---");
        if(list ==null){
            return this.restCallStudent();
        }else if(!list.isEmpty()){
            return list.remove(0);
        }else if (list.isEmpty()){
            list = null;
            return null;
        }
        return null;
    }
}
