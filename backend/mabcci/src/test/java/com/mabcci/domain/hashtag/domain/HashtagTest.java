package com.mabcci.domain.hashtag.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HashtagTest {

    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
    }

    @DisplayName("Hashtag 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtag).isNotNull(),
                () -> assertThat(hashtag).isExactlyInstanceOf(Hashtag.class)
        );
    }

    @DisplayName("Hashtag 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Hashtag hashtag = new Hashtag();

        assertAll(
                () -> assertThat(hashtag).isNotNull(),
                () -> assertThat(hashtag).isExactlyInstanceOf(Hashtag.class)
        );
    }

    @DisplayName("Hastag 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(hashtag, "id", 1L);

        assertAll(
                () -> assertThat(hashtag.id()).isEqualTo(1L),
                () -> assertThat(hashtag.name()).isEqualTo("해시태그")
        );
    }

    @DisplayName("Hashtag 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Hashtag invalidHashtag = new Hashtag();

        final Set<ConstraintViolation<Hashtag>> invalidPropertiesOfValidHashtag
                = validator.validate(hashtag);
        final Set<ConstraintViolation<Hashtag>> invalidPropertiesOfInvalidHashtag
                = validator.validate(invalidHashtag);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidHashtag.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidHashtag.size()).isEqualTo(1)
        );
    }
}
