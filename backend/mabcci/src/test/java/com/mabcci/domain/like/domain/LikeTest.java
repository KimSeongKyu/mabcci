package com.mabcci.domain.like.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LikeTest {

    private Like like;

    @BeforeEach
    void setUp() {
        like = new Like(MEMBER);
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

    @DisplayName("Like 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(like, "status", true);

        assertAll(
                () -> assertThat(like.status()).isTrue(),
                () -> assertThat(like.member()).isEqualTo(MEMBER)
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
