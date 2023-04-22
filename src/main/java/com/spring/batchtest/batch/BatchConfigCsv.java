package com.spring.batchtest.batch;


import com.spring.batchtest.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.List;

@Configuration
public class BatchConfigCsv {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;



    @Bean
    public Job csvJob(){
        return jobBuilderFactory.get("csvJob")
                .start(csxStep())
                .build();
    }
    @Bean
    public Step csxStep(){
     return    stepBuilderFactory.get("csvStep")
                .<Student,Student>chunk(1)
                .reader(csvFileReader())
                .writer(csvWriter())
                .build();
    }
    @Bean
    public ItemWriter<Student> csvWriter(){
         var s= new ItemWriter<Student>() {
            @Override
            public void write(List<? extends Student> items) throws Exception {
                System.out.println("this is from csv item writer ...");
                items.stream().forEach(System.out::println);
            }
        };
        return s;

    }

    @Bean
    public FlatFileItemReader<Student> csvFileReader(){
        FlatFileItemReader<Student> f = new FlatFileItemReader();
        //set resources which need resource file
        f.setResource(new FileSystemResource(new File("D:\\springClass\\batchTest\\inputs\\items.csv")));

        //set LineMapper
        f.setLineMapper(new DefaultLineMapper<Student>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        //setDelimiter(); use default ,
                        setNames("ID" ,"First Name" , "Last Name");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>(){
                    {
                        setTargetType(Student.class);
                    }
                });
            }

            });

        //set skip line
        f.setLinesToSkip(1);

        return f;
    }


}
