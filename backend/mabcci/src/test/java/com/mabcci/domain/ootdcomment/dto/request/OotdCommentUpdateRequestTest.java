package com.mabcci.domain.ootdcomment.dto.request;

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

class OotdCommentUpdateRequestTest {

    private OotdCommentUpdateRequest ootdCommentUpdateRequest;

    @BeforeEach
    void setUp() {
        ootdCommentUpdateRequest = new OotdCommentUpdateRequest(NICKNAME, "수정된 내용");
    }

    @DisplayName("OotdCommentUpdateRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentUpdateRequest).isNotNull(),
                () -> assertThat(ootdCommentUpdateRequest).isExactlyInstanceOf(OotdCommentUpdateRequest.class)
        );
    }

    @DisplayName("OotdCommentUpdateRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdCommentUpdateRequest.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdCommentUpdateRequest.getContent()).isEqualTo("수정된 내용")
        );
    }

    @DisplayName("OotdCommentUpdateRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdCommentUpdateRequest invalidRequest = new OotdCommentUpdateRequest(null, "");

        final Set<ConstraintViolation<OotdCommentUpdateRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdCommentUpdateRequest);
        final Set<ConstraintViolation<OotdCommentUpdateRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
