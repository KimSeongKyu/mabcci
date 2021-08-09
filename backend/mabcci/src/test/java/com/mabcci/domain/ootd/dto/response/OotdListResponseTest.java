package com.mabcci.domain.ootd.dto.response;

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

class OotdListResponseTest {

    private OotdListResponse ootdListResponse;
    private List<OotdResponse> ootdResponses;

    @BeforeEach
    void setUp() {
        ootdResponses = new ArrayList<>(List.of(
                new OotdResponse(1L, "닉네임1", "url/name1.png",
                        new ArrayList<>(List.of("해시태그1", "해시태그2")), 10L),
                new OotdResponse(1L, "닉네임2", "url/name2.png",
                        new ArrayList<>(List.of("해시태그1", "해시태그3")), 20L)
        ));
        ootdListResponse = new OotdListResponse(ootdResponses, 1);
    }

    @DisplayName("OotdListResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdListResponse).isNotNull(),
                () -> assertThat(ootdListResponse).isExactlyInstanceOf(OotdListResponse.class)
        );
    }

    @DisplayName("OotdListResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdListResponse.getOotdResponses()).isEqualTo(ootdResponses),
                () -> assertThat(ootdListResponse.getTotalPages()).isEqualTo(1L)
        );
    }

    @DisplayName("OotdListResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdListResponse invalidResponse = new OotdListResponse(new ArrayList<>(), -1);

        final Set<ConstraintViolation<OotdListResponse>> invalidPropertiesOfValidResponse =
                validator.validate(ootdListResponse);
        final Set<ConstraintViolation<OotdListResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(2)
        );
    }
}
