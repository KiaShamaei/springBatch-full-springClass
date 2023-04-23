package com.spring.batchtest.batch.service;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobService {
    private final JobLauncher jobLauncher;
    private final Job firstJob;
    private final Job csvJob;
    private  final Job jsonJob;

    private final Job restJob;

    public JobService(JobLauncher jobLauncher,
                      @Qualifier("firstjob") Job firstJob,
                      @Qualifier("csvJob") Job csvJob,
                      @Qualifier("jsonJob") Job jsonJob,
                      @Qualifier("restJob") Job restJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
        this.csvJob = csvJob;
        this.jsonJob = jsonJob;
        this.restJob = restJob;
    }
    @Async
    public void firstJobRunnerRest() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(firstJob,j);
    }


//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void firstJobRunnerScheduling() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        System.out.println("job call from schedule--------------->");
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(firstJob,j);
    }

//    @Scheduled(cron = "0/30 * * 1/1 * ?")
    public void csvJoblancher() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        System.out.println("job call from schedule--------------->");
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(csvJob,j);
    }

    @Scheduled(cron = "0/30 * * 1/1 * ?")
    public void jsonJoblancher() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        System.out.println("job call from schedule json--------------->");
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(jsonJob,j);
    }

//    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void restJoblancher() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        System.out.println("job call from schedule rest--------------->"+ System.currentTimeMillis());
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(restJob,j);
    }

}
