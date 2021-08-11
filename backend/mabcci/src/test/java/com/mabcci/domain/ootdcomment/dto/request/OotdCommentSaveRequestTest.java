package com.mabcci.domain.ootdcomment.dto.request;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdCommentSaveRequestTest {

    private OotdCommentSaveRequest ootdCommentSaveRequest;

    @BeforeEach
    void setUp() {
        ootdCommentSaveRequest = new OotdCommentSaveRequest(1L, NICKNAME, null, "내용");
    }

    @DisplayName("OotdCommentRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentSaveRequest).isNotNull(),
                () -> assertThat(ootdCommentSaveRequest).isExactlyInstanceOf(OotdCommentSaveRequest.class)
        );
    }

    @DisplayName("OotdCommentRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdCommentSaveRequest.getOotdId()).isEqualTo(1L),
                () -> assertThat(ootdCommentSaveRequest.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdCommentSaveRequest.getParentCommentId()).isNull(),
                () -> assertThat(ootdCommentSaveRequest.getContent()).isEqualTo("내용")
        );
    }

    @DisplayName("OotdCommentRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdCommentSaveRequest invalidRequest = new OotdCommentSaveRequest(0L, Nickname.of(""), 0L, "");

        final Set<ConstraintViolation<OotdCommentSaveRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdCommentSaveRequest);
        final Set<ConstraintViolation<OotdCommentSaveRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(4)
        );
    }
}
