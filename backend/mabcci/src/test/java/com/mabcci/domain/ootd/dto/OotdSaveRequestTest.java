package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdSaveRequestTest {

    public static final OotdSaveRequest OOTD_REGISTER_REQUEST =
            new OotdSaveRequest(MEMBER, "내용", "상의", "하의", "신발", "악세사리");
    private OotdSaveRequest ootdSaveRequest;

    @BeforeEach
    void setUp() {
        ootdSaveRequest = new OotdSaveRequest(MEMBER, "내용", "상의", "하의", "신발", "악세사리");
    }

    @DisplayName("OotdRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdSaveRequest).isNotNull(),
                () -> assertThat(ootdSaveRequest).isExactlyInstanceOf(OotdSaveRequest.class)
        );
    }

    @DisplayName("OotdRegisterRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdSaveRequest.getMember()).isEqualTo(MEMBER),
                () -> assertThat(ootdSaveRequest.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdSaveRequest.getTop()).isEqualTo("상의"),
                () -> assertThat(ootdSaveRequest.getBottom()).isEqualTo("하의"),
                () -> assertThat(ootdSaveRequest.getShoes()).isEqualTo("신발"),
                () -> assertThat(ootdSaveRequest.getAccessory()).isEqualTo("악세사리")
        );
    }


    @DisplayName("OotdRegisterRequest 인스턴스 프토퍼티 유효성 검증 테스트")
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
