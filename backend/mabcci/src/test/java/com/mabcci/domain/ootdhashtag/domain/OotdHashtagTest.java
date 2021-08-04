package com.mabcci.domain.ootdhashtag.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.domain.hashtag.domain.HashtagTest.HASHTAG;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdHashtagTest {

    private OotdHashtag ootdHashtag;

    @BeforeEach
    void setUp() {
        ootdHashtag = OotdHashtag.builder()
                .ootd(OOTD)
                .hashtag(HASHTAG)
                .build();
    }

    @DisplayName("OotdHashtag 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdHashtag ootdHashtag = new OotdHashtag();

        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootdHashtag, "id", 1L);

        assertAll(
                () -> assertThat(ootdHashtag.id()).isEqualTo(1L),
                () -> assertThat(ootdHashtag.ootd()).isEqualTo(OOTD),
                () -> assertThat(ootdHashtag.hashtag()).isEqualTo(HASHTAG)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdHashtag invalidOotdHashtag = OotdHashtag.builder().build();

        final Set<ConstraintViolation<OotdHashtag>> invalidPropertiesOfValidOotdHashtag =
                validator.validate(ootdHashtag);
        final Set<ConstraintViolation<OotdHashtag>> invalidPropertiesOfInvalidOotdHashtag =
                validator.validate(invalidOotdHashtag);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdHashtag.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdHashtag.size()).isEqualTo(2)
        );
    }
}
