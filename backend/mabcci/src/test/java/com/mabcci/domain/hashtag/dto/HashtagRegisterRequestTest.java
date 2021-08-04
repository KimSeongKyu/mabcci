package com.mabcci.domain.hashtag.dto;

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

class HashtagRegisterRequestTest {

    private List<String> names;
    private HashtagRegisterRequest hashtagRegisterRequest;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>(List.of(
                "해시태그1", "해시태그2"
        ));
        hashtagRegisterRequest = new HashtagRegisterRequest(names);
    }

    @DisplayName("HashtagRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagRegisterRequest).isNotNull(),
                () -> assertThat(hashtagRegisterRequest).isExactlyInstanceOf(HashtagRegisterRequest.class)
        );
    }

    @DisplayName("HashtagRegisterRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagRegisterRequest.getNames()).isEqualTo(names);
    }

    @DisplayName("HashtagRegisterRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final HashtagRegisterRequest invalidRequest = new HashtagRegisterRequest(new ArrayList<>());

        final Set<ConstraintViolation<HashtagRegisterRequest>> invalidPropertiesOfValidRequest =
                validator.validate(hashtagRegisterRequest);
        final Set<ConstraintViolation<HashtagRegisterRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
