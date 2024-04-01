package org.example.apps.taskbatch.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * @author rival
 * @since 2024-02-17
 */

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduleConfig {

    private final Job createTodoJob;
    private final JobLauncher jobLauncher;



    @Scheduled(cron = "35 11 11 11 * *", zone ="UTC" ) // Cron expression for midnight execution
    public void runBatchJob() {

        System.out.println("Hello World");
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();

        try {
            jobLauncher.run(createTodoJob, jobParameters);
        } catch (Exception e) {
            log.error("Batch Error",e);
        }
    }

}
