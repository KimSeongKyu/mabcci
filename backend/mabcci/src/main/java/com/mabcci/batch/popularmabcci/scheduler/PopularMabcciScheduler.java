package com.mabcci.batch.popularmabcci.scheduler;

import com.mabcci.batch.popularmabcci.job.config.PopularMabcciJobConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PopularMabcciScheduler {

    private final static String EVERY_ZERO_AM_CRON_EXPRESSION = "0 0 0 * * *";
    private final static String EVERY_MINUTE_CRON_EXPRESSION_FOR_TEST = "0 0/1 * * * *";


    private final JobLauncher jobLauncher;
    private final PopularMabcciJobConfig popularMabcciJobConfig;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public PopularMabcciScheduler(final JobLauncher jobLauncher, final PopularMabcciJobConfig popularMabcciJobConfig) {
        this.jobLauncher = jobLauncher;
        this.popularMabcciJobConfig = popularMabcciJobConfig;
    }

    @Scheduled(cron = EVERY_MINUTE_CRON_EXPRESSION_FOR_TEST)
    public void runJob() {
        try {
            jobLauncher.run(popularMabcciJobConfig.popularMabcciJob(),
                    new JobParameters(Map.of("time", new JobParameter(System.currentTimeMillis()))));
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
            log.error(e.getMessage());
        }
    }
}
