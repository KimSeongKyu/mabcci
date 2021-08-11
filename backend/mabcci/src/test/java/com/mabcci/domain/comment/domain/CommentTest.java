package com.mabcci.domain.comment.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommentTest {

    private Member member;
    private Comment parentComment;

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
        parentComment = new Comment(member, Optional.empty(), "내용");
    }

    @DisplayName("Comment 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(parentComment).isNotNull(),
                () -> assertThat(parentComment).isExactlyInstanceOf(Comment.class)
        );
    }

    @DisplayName("Comment 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Comment comment = new Comment();

        assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment).isExactlyInstanceOf(Comment.class)
        );
    }

    @DisplayName("Comment 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(parentComment.member()).isEqualTo(member),
                () -> assertThat(parentComment.parentComment()).isEmpty(),
                () -> assertThat(parentComment.content()).isEqualTo("내용")
        );
    }
}
