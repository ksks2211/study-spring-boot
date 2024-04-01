package org.example.apps.taskbatch;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author rival
 * @since 2024-02-17
 */


@SpringBootTest
class ScheduleConfigTest {


    @Autowired
    @Qualifier("createTodoJob")
    public Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    public JobLauncherTestUtils jobLauncherTestUtils;

    public JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    public void setUp() {
        jobLauncherTestUtils = new JobLauncherTestUtils();
        jobRepositoryTestUtils = new JobRepositoryTestUtils();

        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);


        jobRepositoryTestUtils.setJobRepository(jobRepository);
        jobRepositoryTestUtils.setJobParametersIncrementer(new RunIdIncrementer());


        jobRepositoryTestUtils.removeJobExecutions();


    }

    @Test
    public void testYourJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();
        // Launch the job
        JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(job, jobParameters);

        // Assert job execution status
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        // Additional assertions...
    }

    @AfterEach
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }
}