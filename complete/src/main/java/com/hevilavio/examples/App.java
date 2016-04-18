package com.hevilavio.examples;

import java.util.Calendar;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.hevilavio.examples.parallelchunking.PaymentOrder;
import com.hevilavio.examples.parallelchunking.PaymentOrderProcessor;
import com.hevilavio.examples.parallelchunking.PaymentOrderReader;
import com.hevilavio.examples.parallelchunking.PaymentOrderWritter;

/**
 * Created by hevilavio on 4/18/16.
 */
@Slf4j
@ComponentScan
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class App {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    JobLauncher jobLauncher;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    /**
     * Every 24 hours
     * */
    @Scheduled(fixedRate = (1000 * 60 * 60 * 24))
    public void executeJob() throws Exception {
        log.info("Starting job execution");

        TaskletStep taskletStep = stepBuilderFactory
            .get("jobName")
            .<PaymentOrder, PaymentOrder>chunk(10)
            .reader(PaymentOrderReader.getReader())
            .processor(new PaymentOrderProcessor())
            .writer(new PaymentOrderWritter())
            .build();

        Job job = jobBuilderFactory
            .get("myJobName")
            .incrementer(new RunIdIncrementer())
            .flow(taskletStep)
            .end()
            .build();

        JobExecution jobExecution = jobLauncher.run(job, getParameters());

        log.info("Job executed, status={}", jobExecution.getExitStatus());

    }

    public JobParameters getParameters() {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addLong("time", Calendar.getInstance().getTimeInMillis());
        return builder.toJobParameters();
    }


}
