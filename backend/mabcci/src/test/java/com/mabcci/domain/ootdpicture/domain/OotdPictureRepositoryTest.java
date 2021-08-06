package com.mabcci.domain.ootdpicture.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class OotdPictureRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OotdPictureRepository ootdPictureRepository;

    private Member member;
    private Ootd ootd;
    private OotdPicture ootdPicture;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
        ootdPicture = OotdPicture.builder()
                .ootd(ootd)
                .url("testUrl")
                .fileName("testFileName")
                .build();
    }

    @DisplayName("OotdPictureRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPictureRepository).isNotNull(),
                () -> assertThat(ootdPictureRepository).isInstanceOf(OotdPictureRepository.class)
        );
    }

    @DisplayName("OotdPictureRepository save 기능 테스트")
    @Test
    void save_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);

        final OotdPicture savedOotdPicture = ootdPictureRepository.save(ootdPicture);

        assertThat(savedOotdPicture.id()).isEqualTo(ootdPicture.id());
    }
}
