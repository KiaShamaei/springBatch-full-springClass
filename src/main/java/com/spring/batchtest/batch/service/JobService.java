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
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobService {
    private final JobLauncher jobLauncher;
    private final Job firstJob;

    public JobService(JobLauncher jobLauncher,
                      @Qualifier("firstjob") Job firstJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
    }
    public void firstJobRunner() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("current" , new JobParameter(System.currentTimeMillis()));
        //make JobParameters --->
        JobParameters j = new JobParameters(param);
        jobLauncher.run(firstJob,j);
    }

}
