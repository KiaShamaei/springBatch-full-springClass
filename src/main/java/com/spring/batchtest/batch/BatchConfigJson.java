package com.spring.batchtest.batch;


import com.spring.batchtest.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.List;

@Configuration
public class BatchConfigJson {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jsonJob(){
       return jobBuilderFactory.get("jsonJob")
                .start(jsonStep() )
                .build();
    }
    @Bean
    public Step jsonStep(){
        return stepBuilderFactory.get("jsonStep")
                .<Student,Student>chunk(1)
                .reader(jsonItemReader())
                .writer(jsonWriter())
                .build();
    }
    @Bean
    public ItemWriter<Student> jsonWriter(){

        ItemWriter<Student> item = new ItemWriter<Student>() {
            @Override
            public void write(List<? extends Student> items) throws Exception {
                System.out.println("this is from json item writer ---->");
                items.stream().forEach(System.out::println);
            }
        };
        return item;
    }
    @Bean
    public JsonItemReader<Student> jsonItemReader(){
        JsonItemReader<Student> itemReader = new JsonItemReader<>();
        itemReader.setResource(new FileSystemResource(new File("D:\\springClass\\batchTest\\inputs\\item.json")));
        itemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(Student.class));
        return itemReader;
    }


}
