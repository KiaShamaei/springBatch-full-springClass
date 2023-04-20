package com.spring.batchtest.batch;


import com.spring.batchtest.batch.batchChunkItems.FirstItemProcessor;
import com.spring.batchtest.batch.batchChunkItems.FirstItemReader;
import com.spring.batchtest.batch.batchChunkItems.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfigChunk {
    @Autowired
    private  JobBuilderFactory jobBuilderFactory;
    @Autowired
    private  StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FirstItemReader firstItemReader;
    @Autowired
    private FirstItemProcessor firstItemProcessor;
    @Autowired
    private FirstItemWriter firstItemWriter;

    @Bean
    public Job firstjob(){
        return jobBuilderFactory.get("first job name")
                .start(firstStep())
                .build();
    }

    @Bean
    public Step firstStep(){
       return stepBuilderFactory.get("first step")
                .<Integer , Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
