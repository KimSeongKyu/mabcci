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

public class OotdSaveRequestTest {

    private OotdSaveRequest ootdSaveRequest;

    @BeforeEach
    void setUp() {
        ootdSaveRequest = new OotdSaveRequest("닉네임", "내용", "상의", "하의", "신발", "악세사리");
    }

    @DisplayName("OotdSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdSaveRequest).isNotNull(),
                () -> assertThat(ootdSaveRequest).isExactlyInstanceOf(OotdSaveRequest.class)
        );
    }

    @DisplayName("OotdSaveRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdSaveRequest.getNickname()).isEqualTo("닉네임"),
                () -> assertThat(ootdSaveRequest.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdSaveRequest.getTop()).isEqualTo("상의"),
                () -> assertThat(ootdSaveRequest.getBottom()).isEqualTo("하의"),
                () -> assertThat(ootdSaveRequest.getShoes()).isEqualTo("신발"),
                () -> assertThat(ootdSaveRequest.getAccessory()).isEqualTo("악세사리")
        );
    }


    @DisplayName("OotdSaveRequest 인스턴스 프토퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdSaveRequest invalidRequest = new OotdSaveRequest(
                null, null, null, null, null, null);

        final Set<ConstraintViolation<OotdSaveRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdSaveRequest);
        final Set<ConstraintViolation<OotdSaveRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
