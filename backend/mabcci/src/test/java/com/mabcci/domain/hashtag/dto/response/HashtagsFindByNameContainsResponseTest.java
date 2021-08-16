package com.mabcci.domain.hashtag.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagsFindByNameContainsResponseTest {

    private HashtagsFindByNameContainsResponse hashtagsFindByNameContainsResponse;

    @BeforeEach
    void setUp() {
        hashtagsFindByNameContainsResponse =
                new HashtagsFindByNameContainsResponse(List.of("해시태그1", "해시태그2"));
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagsFindByNameContainsResponse).isNotNull(),
                () -> assertThat(hashtagsFindByNameContainsResponse)
                        .isExactlyInstanceOf(HashtagsFindByNameContainsResponse.class)
        );
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagsFindByNameContainsResponse.hashtags()).contains("해시태그1", "해시태그2");
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final HashtagsFindByNameContainsResponse invalidResponse =
                new HashtagsFindByNameContainsResponse(null);

        final Set<ConstraintViolation<HashtagsFindByNameContainsResponse>> invalidPropertiesOfValidResponse =
                validator.validate(hashtagsFindByNameContainsResponse);
        final Set<ConstraintViolation<HashtagsFindByNameContainsResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
