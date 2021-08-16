package com.mabcci.domain.hashtag.dto;

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

class HashtagFindByNicknameContainsResponseTest {

    private HashtagFindByNicknameContainsResponse hashtagFindByNicknameContainsResponse;

    @BeforeEach
    void setUp() {
        hashtagFindByNicknameContainsResponse =
                new HashtagFindByNicknameContainsResponse(List.of("해시태그1", "해시태그2"));
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagFindByNicknameContainsResponse).isNotNull(),
                () -> assertThat(hashtagFindByNicknameContainsResponse)
                        .isExactlyInstanceOf(HashtagFindByNicknameContainsResponse.class)
        );
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagFindByNicknameContainsResponse.hashtags()).contains("해시태그1", "해시태그2");
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final HashtagFindByNicknameContainsResponse invalidResponse =
                new HashtagFindByNicknameContainsResponse(null);

        final Set<ConstraintViolation<HashtagFindByNicknameContainsResponse>> invalidPropertiesOfValidResponse =
                validator.validate(hashtagFindByNicknameContainsResponse);
        final Set<ConstraintViolation<HashtagFindByNicknameContainsResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
