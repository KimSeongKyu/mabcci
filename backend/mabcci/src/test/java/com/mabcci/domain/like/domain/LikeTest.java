package com.mabcci.domain.like.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LikeTest {

    private Member member;
    private Like like;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .memberRole(MemberRole.USER)
                .build();
        like = new Like(member);
    }

    @DisplayName("Like 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }

    @DisplayName("Like 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Like like = new Like();

        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }

    @DisplayName("Like 인스턴스 좋아요 상태 변경 테스트")
    @Test
    void update_status_test() {
        ReflectionTestUtils.setField(like, "status", true);
        like = like.updateStatus();

        assertThat(like.status()).isFalse();
    }

    @DisplayName("Like 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(like, "status", true);

        assertAll(
                () -> assertThat(like.status()).isTrue(),
                () -> assertThat(like.member()).isEqualTo(member)
        );
    }

    @DisplayName("Like 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Like invalidLike = new Like();

        final Set<ConstraintViolation<Like>> invalidPropertiesOfValidLike = validator.validate(like);
        final Set<ConstraintViolation<Like>> invalidPropertiesOfInvalidLike = validator.validate(invalidLike);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidLike.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidLike.size()).isEqualTo(1)
        );
    }
}
