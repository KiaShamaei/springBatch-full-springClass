package com.spring.batchtest.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfigTasklet {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    public BatchConfigTasklet(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job jobBuild(){
        return jobBuilderFactory.get("firstJob")
                .start(step1())
                .incrementer(new RunIdIncrementer())
                .next(step2())
                .incrementer(new RunIdIncrementer())
                .build();
    }
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("firstStep")
                .tasklet(tasklet1())
                .build();
    }
    @Bean
    public Tasklet tasklet1(){
        return (stepContributor,chunkContext)->{
            System.out.println("this is first job");
            return RepeatStatus.FINISHED;
        };
    }
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("secondStep")
                .tasklet(tasklet2())
                .build();
    }
    @Bean
    public Tasklet tasklet2(){
        return (stepContributor,chunkContext)->{
            System.out.println("this is second job");
            return RepeatStatus.FINISHED;
        };
    }



}
