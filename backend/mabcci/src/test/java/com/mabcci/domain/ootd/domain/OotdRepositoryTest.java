package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class OotdRepositoryTest {

    private Ootd ootd;
    private Member member;

    @Autowired
    private OotdRepository ootdRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MAN)
                .memberRole(USER)
                .build();

        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
    }

    @DisplayName("OotdRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdRepository).isNotNull(),
                () -> assertThat(ootdRepository).isInstanceOf(OotdRepository.class)
        );
    }

    @DisplayName("OotdRepository save 기능 테스트")
    @Test
    void save_test() {
        testEntityManager.persist(member);

        final Ootd savedOotd = ootdRepository.save(ootd);

        assertThat(savedOotd.id()).isEqualTo(ootd.id());
    }


    @DisplayName("OotdRepository findById 기능 테스트")
    @Test
    void find_by_id_test() {
        testEntityManager.persist(member);
        ootdRepository.save(ootd);

        final Ootd foundOotd = ootdRepository.findById(ootd.id()).get();

        assertThat(foundOotd.id()).isEqualTo(ootd.id());
    }
}
