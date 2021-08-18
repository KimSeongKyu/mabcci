package com.mabcci.batch.popularmabcci.job.config;

import com.mabcci.domain.member.domain.Member;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class PopularMabcciJobConfig {

    private final static int CHUNK_SIZE = 10;
    private final static int ONE = 1;
    private final static int MAX_POPULAR_MABCCI_COUNT = 8;
    private final static boolean IS_POPULAR_MABCCI = true;
    private final static boolean IS_NOT_POPULAR_MABCCI = false;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private int popularMabcciCount;

    public PopularMabcciJobConfig(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory,
                                  final EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        popularMabcciCount = 0;
    }

    @Bean
    public Job popularMabcciJob() {
        return jobBuilderFactory.get("popularMabcciJob")
                .start(popularMabcciClearStep())
                .next(popularMabcciUpdateStep())
                .build();
    }

    @Bean
    public Step popularMabcciClearStep() {
        return stepBuilderFactory.get("popularMabcciClearStep")
                .<Member, Member>chunk(CHUNK_SIZE)
                .reader(popularMabcciClearPagingItemReader())
                .processor(popularMabcciClearItemProcessor())
                .writer(popularMabcciItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Member> popularMabcciClearPagingItemReader() {
        return new JpaPagingItemReaderBuilder<Member>()
                .name("popularMabcciClearPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("SELECT m FROM Member m WHERE m.isPopularMabcci = TRUE")
                .build();
    }

    @Bean
    public ItemProcessor<Member, Member> popularMabcciClearItemProcessor() {
        return member -> member.updateToPopularMabcci(IS_NOT_POPULAR_MABCCI);
    }

    @Bean
    public JpaItemWriter<Member> popularMabcciItemWriter() {
        return new JpaItemWriterBuilder()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step popularMabcciUpdateStep() {
        return stepBuilderFactory.get("popularMabcciUpdateStep")
                .<Member, Member>chunk(CHUNK_SIZE)
                .reader(popularMabcciUpdatePagingItemReader())
                .processor(popularMabcciUpdateItemProcessor())
                .writer(popularMabcciItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Member> popularMabcciUpdatePagingItemReader() {
        return new JpaPagingItemReaderBuilder<Member>()
                .name("popularMabcciUpdatePagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("SELECT m FROM Member m JOIN m.followers f GROUP BY f.following ORDER BY count(f.following)")
                .build();
    }

    @Bean
    public ItemProcessor<Member, Member> popularMabcciUpdateItemProcessor() {
        return member -> {
            if (popularMabcciCount < MAX_POPULAR_MABCCI_COUNT) {
                Integer.sum(popularMabcciCount, ONE);
                return member.updateToPopularMabcci(IS_POPULAR_MABCCI);
            }
            return member;
        };
    }
}
