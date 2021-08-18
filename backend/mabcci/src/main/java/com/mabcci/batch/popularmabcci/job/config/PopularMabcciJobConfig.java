package com.mabcci.batch.popularmabcci.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopularMabcciJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public PopularMabcciJobConfig(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job popularMabcciJob() {
        return jobBuilderFactory.get("popularMabcciJob")
                .start(popularMabcciStep())  // Step 설정
                .build();
    }

    @Bean
    public Step popularMabcciStep() {
        return stepBuilderFactory.get("popularMabcciStep")
                .tasklet(new TutorialTasklet()) // Tasklet 설정
                .build();
    }
}
