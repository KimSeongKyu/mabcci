package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdCommentResponseTest {

    private OotdCommentResponse ootdCommentResponse;
    private LocalDateTime now = LocalDateTime.now();
    private Member member;
    private Ootd ootd;
    private OotdComment ootdComment;

    @BeforeEach
    void setUp() {
        ootdCommentResponse = new OotdCommentResponse("testUrl", NICKNAME, now, now, "내용", new ArrayList<>());
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
        ootdComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .parentComment(null)
                .content("내용")
                .build();
    }

    @DisplayName("OotdCommentResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentResponse).isNotNull(),
                () -> assertThat(ootdCommentResponse).isExactlyInstanceOf(OotdCommentResponse.class)
        );
    }

    @DisplayName("OotdCommentResponse 클래스 스태틱 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_method_constructor_test() {
        final OotdCommentResponse ootdCommentResponse =
                OotdCommentResponse.ofOotdCommentWithChildren(ootdComment, Collections.emptyList());

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
