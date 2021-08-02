package com.mabcci.domain.member.domain;

import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.domain.model.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.category.domain.CategoryTest.CATEGORY;
import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static com.mabcci.domain.model.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MemberTest {

    public static final Member MEMBER = Member.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .nickname(NICKNAME)
            .phone(PHONE)
            .gender(Gender.MALE)
            .role(MemberRole.USER)
            .build();

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

    @DisplayName("Member 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class)
        );

    }

    @DisplayName("Member 인스턴스의 password 일치 여부 테스트")
    @Test
    void passwordCheck_test() {
        final Password invalidPassword = Password.of("invalidPassword");

        assertAll(
                () -> assertThat(member.checkPassword(PASSWORD)).isTrue(),
                () -> assertThat(member.checkPassword(invalidPassword)).isFalse()
        );
    }

    @DisplayName("Member 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(member, "id", 1L);

        assertAll(
                () -> assertThat(member.id()).isEqualTo(1L),
                () -> assertThat(member.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(member.role()).isEqualTo(MemberRole.USER)
        );
    }

    @DisplayName("Member 인스턴스의 MemberCategory 값 추가 테스트")
    @Test
    void addMemberCategory_test() {
        final MemberCategory memberCategory = MemberCategory.createMemberCategory(MEMBER, CATEGORY);
        member.addMemberCategory(memberCategory);

        assertThat(member.memberCategories().size()).isEqualTo(1);
    }

}