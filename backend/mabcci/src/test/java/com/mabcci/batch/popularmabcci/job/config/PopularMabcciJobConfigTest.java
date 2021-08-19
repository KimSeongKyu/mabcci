package com.mabcci.batch.popularmabcci.job.config;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.follow.domain.FollowRepository;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.stream.IntStream;

import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
class PopularMabcciJobConfigTest {

    @Autowired private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired private JobRepositoryTestUtils jobRepositoryTestUtils;
    @Autowired private MemberRepository memberRepository;
    @Autowired private FollowRepository followRepository;

    @BeforeEach
    void setUp() {
        final String email = "example@example.com";
        final String nickname = "nickname";
        final String phone = "010-5234-567";
        IntStream.rangeClosed(0, 100)
                .forEachOrdered(i -> {
                    final Member mabcci = Member.Builder()
                            .email(Email.of(i + email))
                            .password(PASSWORD)
                            .nickname(Nickname.of(nickname + i))
                            .phone(Phone.of(phone + i))
                            .gender(Gender.MAN)
                            .memberRole(MemberRole.MABCCI)
                            .build();
                    memberRepository.save(mabcci);
                    IntStream.rangeClosed(0, i)
                            .forEachOrdered(j -> {
                                final Member follower = Member.Builder()
                                        .email(Email.of(email + 200 + i + j))
                                        .password(PASSWORD)
                                        .nickname(Nickname.of(nickname + 200 + i + j))
                                        .phone(Phone.of(phone + 200 + i + j))
                                        .gender(Gender.MAN)
                                        .memberRole(MemberRole.USER)
                                        .build();
                                final Follow follow = Follow.Builder()
                                        .follower(follower)
                                        .following(mabcci)
                                        .build();
                                memberRepository.save(follower);
                                followRepository.save(follow);
                            });
                });
    }

    @AfterEach
    void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
        followRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void popular_mabcci_clear_step_test() {
        assertAll(
                () -> memberRepository.findAll()
                        .stream()
                        .forEach(mabcci -> {
                            ReflectionTestUtils.setField(mabcci, "isPopularMabcci", true);
                            assertThat(mabcci.isPopularMabcci()).isTrue();
                        })
        );

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("popularMabcciClearStep");
        Collection actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        assertThat(actualStepExecutions.size()).isEqualTo(1);
        assertThat(actualJobExitStatus.getExitCode()).isEqualTo("COMPLETED");

        assertAll(
                () -> memberRepository.findAll()
                        .stream()
                        .forEach(mabcci -> assertThat(mabcci.isPopularMabcci()).isFalse())
        );
    }

    @Test
    void popular_mabcci_update_step_test() {
        assertAll(
                () -> memberRepository.findAll()
                        .stream()
                        .forEach(mabcci -> {
                            ReflectionTestUtils.setField(mabcci, "isPopularMabcci", false);
                            assertThat(mabcci.isPopularMabcci()).isFalse();
                        })
        );

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("popularMabcciUpdateStep");
        Collection actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        assertThat(actualStepExecutions.size()).isEqualTo(1);
        assertThat(actualJobExitStatus.getExitCode()).isEqualTo("COMPLETED");


        final long popularMabcciCount = memberRepository.findAll()
                .stream()
                .filter(Member::isPopularMabcci)
                .count();
        assertThat(popularMabcciCount).isEqualTo(8L);
    }
}
