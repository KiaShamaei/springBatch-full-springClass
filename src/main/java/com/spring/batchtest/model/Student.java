package com.spring.batchtest.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Student {

    private Long id;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
}
