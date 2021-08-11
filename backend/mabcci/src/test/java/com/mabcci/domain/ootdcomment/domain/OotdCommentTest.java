package com.mabcci.domain.ootdcomment.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdCommentTest {

    private Member member;
    private Ootd ootd;
    private OotdComment parentComment;
    private OotdComment childComment;

    @BeforeEach
    void setUp() {
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
        parentComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .parentComment(Optional.empty())
                .content("내용")
                .build();
        childComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .parentComment(Optional.of(parentComment))
                .content("내용")
                .build();
    }

    @DisplayName("OotdComment 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(parentComment).isNotNull(),
                () -> assertThat(parentComment).isExactlyInstanceOf(OotdComment.class)
        );
    }

    @DisplayName("OotdComment 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdComment ootdComment = new OotdComment();

        assertAll(
                () -> assertThat(ootdComment).isNotNull(),
                () -> assertThat(ootdComment).isExactlyInstanceOf(OotdComment.class)
        );
    }

    @DisplayName("OotdComment 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(parentComment, "id", 1L);
        ReflectionTestUtils.setField(parentComment, "childComments", new HashSet<>(Set.of(childComment)));

        assertAll(
                () -> assertThat(parentComment.id()).isEqualTo(1L),
                () -> assertThat(parentComment.member()).isEqualTo(member),
                () -> assertThat(parentComment.ootd()).isEqualTo(ootd),
                () -> assertThat(parentComment.parentComment()).isEmpty(),
                () -> assertThat(parentComment.childComments().contains(childComment)).isTrue(),
                () -> assertThat(parentComment.content()).isEqualTo("내용"),
                () -> assertThat(childComment.parentComment().get()).isEqualTo(parentComment)
        );
    }

    @DisplayName("OotdComment 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdComment invalidOotdComment = OotdComment.builder()
                .member(null)
                .ootd(null)
                .parentComment(null)
                .content("")
                .build();

        final Set<ConstraintViolation<OotdComment>> invalidPropertiesOfValidOotdComment =
                validator.validate(parentComment);
        final Set<ConstraintViolation<OotdComment>> invalidPropertiesOfInvalidOotdComment =
                validator.validate(invalidOotdComment);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdComment.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdComment.size()).isEqualTo(4)
        );
    }
}
