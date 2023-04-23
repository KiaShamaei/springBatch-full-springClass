package com.spring.batchtest.batch;

import com.spring.batchtest.batch.service.StudentServiceAdapter;
import com.spring.batchtest.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BatchConfigRest {
    @Autowired
    private JobBuilderFactory jobBuilderFactory ;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentServiceAdapter studentServiceAdapter;

    @Bean
    public Job restJob(){
        Job j = jobBuilderFactory.get("restJob")
                .start(restStep())
                .build();
        return j;
    }
    @Bean
    public Step restStep(){
        Step s = stepBuilderFactory.get("restStep")
                .<Student,Student>chunk(1)
                .reader(itemReaderRest())
                .writer(restItemWriter())
                .build();
        return s;
    }
    @Bean
    public ItemReaderAdapter<Student> itemReaderRest(){
        System.out.println("rest item reader call");
        var itemReader = new ItemReaderAdapter<Student>();
        itemReader.setTargetObject(studentServiceAdapter);
        itemReader.setTargetMethod("getStudent");
        return itemReader;
    }
    @Bean
    public ItemWriter<Student> restItemWriter(){
        var s= new ItemWriter<Student>() {
            @Override
            public void write(List<? extends Student> items) throws Exception {
                System.out.println("this is from rest item writer ...");
                items.stream().forEach(System.out::println);
            }
        };
        return s;

    }
}
