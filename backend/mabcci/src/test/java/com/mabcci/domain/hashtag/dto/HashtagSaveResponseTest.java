package com.mabcci.domain.hashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagSaveResponseTest {

    private HashtagSaveResponse hashtagSaveResponse;
    private List<Hashtag> hashtags;

    @BeforeEach
    void setUp() {
        hashtags = List.of(
                Hashtag.builder()
                        .name("해시태그1")
                        .build(),
                Hashtag.builder()
                        .name("해시태그2")
                        .build()
        );
        hashtagSaveResponse = new HashtagSaveResponse(hashtags);
    }

    @DisplayName("HashtagSaveResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagSaveResponse).isNotNull(),
                () -> assertThat(hashtagSaveResponse).isExactlyInstanceOf(HashtagSaveResponse.class)
        );
    }

    @DisplayName("HashtagSaveResponse 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagSaveResponse.hashtags()).isEqualTo(hashtags);
    }

    @DisplayName("HashtagSaveResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final HashtagSaveResponse invalidResponse = new HashtagSaveResponse(new ArrayList<>());

        final Set<ConstraintViolation<HashtagSaveResponse>> invalidPropertiesOfValidResponse =
                validator.validate(hashtagSaveResponse);
        final Set<ConstraintViolation<HashtagSaveResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
