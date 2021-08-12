package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdCommentResponseTest {

    private OotdCommentResponse ootdCommentResponse;
    private LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        ootdCommentResponse = new OotdCommentResponse("testUrl", NICKNAME, now, now, "내용", new ArrayList<>());
    }

    @DisplayName("OotdCommentResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentResponse).isNotNull(),
                () -> assertThat(ootdCommentResponse).isExactlyInstanceOf(OotdCommentResponse.class)
        );
    }

    @DisplayName("OotdCommentResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdCommentResponse.getMemberPicture()).isEqualTo("testUrl"),
                () -> assertThat(ootdCommentResponse.getMemberNickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdCommentResponse.getCreatedDate()).isEqualTo(now),
                () -> assertThat(ootdCommentResponse.getModifiedDate()).isEqualTo(now),
                () -> assertThat(ootdCommentResponse.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdCommentResponse.getComments()).isEmpty()
        );
    }

    @DisplayName("OotdCommentResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdCommentResponse invalidResponse =
                new OotdCommentResponse("", Nickname.of(null), LocalDateTime.MAX, LocalDateTime.MAX,
                        "", null);

        final Set<ConstraintViolation<OotdCommentResponse>> invalidPropertiesOfValidResponse =
                validator.validate(ootdCommentResponse);
        final Set<ConstraintViolation<OotdCommentResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(6)
        );
    }
}
