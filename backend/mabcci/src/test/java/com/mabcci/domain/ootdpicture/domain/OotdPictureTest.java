package com.mabcci.domain.ootdpicture.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdPictureTest {

    private OotdPicture ootdPicture;
    private Member member;
    private Ootd ootd;
    private Validator validator;

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
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("OotdPicture 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }

    @DisplayName("OotdPicture 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdPicture ootdPicture = new OotdPicture();

        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }

    @DisplayName("OotdPicture 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootdPicture, "id", 1L);

        assertAll(
                () -> assertThat(ootdPicture.id()).isEqualTo(1L),
                () -> assertThat(ootdPicture.ootd()).isEqualTo(ootd),
                () -> assertThat(ootdPicture.url()).isEqualTo("testUrl"),
                () -> assertThat(ootdPicture.fileName()).isEqualTo("testFileName")
        );
    }

    @DisplayName("OotdPicture 인스턴스의 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final OotdPicture invalidOotdPicture = OotdPicture.builder()
                .ootd(null)
                .url("")
                .fileName("")
                .build();
        final Set<ConstraintViolation<OotdPicture>> invalidPropertiesOfValidOotdPicture = validator.validate(ootdPicture);
        final Set<ConstraintViolation<OotdPicture>> invalidPropertiesOfInvalidOotdPicture = validator.validate(invalidOotdPicture);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdPicture.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdPicture.size()).isEqualTo(3)
        );

    }
}
