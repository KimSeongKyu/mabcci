package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdCommentListFindResponseTest {

    private OotdCommentListFindResponse ootdCommentListFindResponse;
    private OotdCommentFindResponse firstOotdCommentFindResponse;
    private OotdCommentFindResponse secondOotdCommentFindResponse;

    @BeforeEach
    void setUp() {
        firstOotdCommentFindResponse = new OotdCommentFindResponse("testUrl1", Nickname.of("닉네임1"),
                now(), now(), "내용1", new ArrayList<>());
        secondOotdCommentFindResponse = new OotdCommentFindResponse("testUrl2", Nickname.of("닉네임2"),
                now(), now(), "내용2", new ArrayList<>());
        ootdCommentListFindResponse = new OotdCommentListFindResponse(List.of(firstOotdCommentFindResponse, secondOotdCommentFindResponse));
    }

    @DisplayName("OotdCommentFindListResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentListFindResponse).isNotNull(),
                () -> assertThat(ootdCommentListFindResponse).isExactlyInstanceOf(OotdCommentListFindResponse.class)
        );
    }

    @DisplayName("OotdCommentFindListResponse 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(ootdCommentListFindResponse.getOotdCommentResponses())
                .containsAll(List.of(firstOotdCommentFindResponse, secondOotdCommentFindResponse));
    }

    @DisplayName("OotdCommentFindListResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdCommentListFindResponse invalidResponse = new OotdCommentListFindResponse(null);

        final Set<ConstraintViolation<OotdCommentListFindResponse>> invalidPropertiesOfValidResponse =
                validator.validate(ootdCommentListFindResponse);
        final Set<ConstraintViolation<OotdCommentListFindResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
