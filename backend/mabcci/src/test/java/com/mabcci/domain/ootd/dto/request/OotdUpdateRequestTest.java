package com.mabcci.domain.ootd.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdUpdateRequestTest {

    private OotdUpdateRequest ootdUpdateRequest;

    @BeforeEach
    void setUp() {
        ootdUpdateRequest = new OotdUpdateRequest("내용", "상의", "하의", "신발", "악세사리");
    }

    @DisplayName("OotdUpdateRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdUpdateRequest).isNotNull(),
                () -> assertThat(ootdUpdateRequest).isExactlyInstanceOf(OotdUpdateRequest.class)
        );
    }

    @DisplayName("OotdUpdateRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdUpdateRequest.content()).isEqualTo("내용"),
                () -> assertThat(ootdUpdateRequest.top()).isEqualTo("상의"),
                () -> assertThat(ootdUpdateRequest.bottom()).isEqualTo("하의"),
                () -> assertThat(ootdUpdateRequest.shoes()).isEqualTo("신발"),
                () -> assertThat(ootdUpdateRequest.accessory()).isEqualTo("악세사리")
        );
    }

    @DisplayName("OotdUpdateRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdUpdateRequest invalidRequest = new OotdUpdateRequest(null, null, null, null, null);

        final Set<ConstraintViolation<OotdUpdateRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdUpdateRequest);
        final Set<ConstraintViolation<OotdUpdateRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
