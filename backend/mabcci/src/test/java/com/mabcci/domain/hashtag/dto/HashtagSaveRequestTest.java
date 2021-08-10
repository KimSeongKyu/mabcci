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

public class HashtagSaveRequestTest {

    public static final HashtagSaveRequest HASHTAG_SAVE_REQUEST =
            new HashtagSaveRequest(new ArrayList<>(List.of(
                    "해시태그1", "해시태그2"
            )));

    private List<String> names;
    private HashtagSaveRequest hashtagSaveRequest;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>(List.of(
                "해시태그1", "해시태그2"
        ));
        hashtagSaveRequest = new HashtagSaveRequest(names);
    }

    @DisplayName("HashtagSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagSaveRequest).isNotNull(),
                () -> assertThat(hashtagSaveRequest).isExactlyInstanceOf(HashtagSaveRequest.class)
        );
    }

    @DisplayName("HashtagSaveRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagSaveRequest.names()).isEqualTo(names);
    }

    @DisplayName("HashtagSaveRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final HashtagSaveRequest invalidRequest = new HashtagSaveRequest(new ArrayList<>());

        final Set<ConstraintViolation<HashtagSaveRequest>> invalidPropertiesOfValidRequest =
                validator.validate(hashtagSaveRequest);
        final Set<ConstraintViolation<HashtagSaveRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
