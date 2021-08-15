package com.mabcci.domain.ootdLike.domain;

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

class OotdLikeTest {

    private Member member;
    private Ootd ootd;
    private OotdLike ootdLike;

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
        ootdLike = OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build();
    }

    @DisplayName("OotdLike 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdLike).isNotNull(),
                () -> assertThat(ootdLike).isInstanceOf(OotdLike.class)
        );
    }

    @DisplayName("OotdLike 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdLike ootdLike = new OotdLike();

        assertAll(
                () -> assertThat(ootdLike).isNotNull(),
                () -> assertThat(ootdLike).isInstanceOf(OotdLike.class)
        );
    }

    @DisplayName("OotdLike 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootdLike, "id", 1L);

        assertAll(
                () -> assertThat(ootdLike.id()).isEqualTo(1L),
                () -> assertThat(ootdLike.ootd()).isEqualTo(ootd)
        );
    }

    @DisplayName("OotdLike 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdLike invalidOotdLike = new OotdLike();

        final Set<ConstraintViolation<OotdLike>> invalidPropertiesOfValidOotdLike =
                validator.validate(ootdLike);
        final Set<ConstraintViolation<OotdLike>> invalidPropertiesOfInvalidOotdLike =
                validator.validate(invalidOotdLike);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdLike.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdLike.size()).isEqualTo(2)
        );
    }
}
