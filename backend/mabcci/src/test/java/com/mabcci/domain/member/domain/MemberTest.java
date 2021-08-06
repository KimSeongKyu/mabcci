package com.mabcci.domain.member.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.member.application.MemberFindServiceTest.CATEGORY_NAME;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MemberTest {

    public static final String DESCRIPTION = "description";
    public static final String PICTURE = "picture";

    private Category category;
    private Member member;

    @BeforeEach
    void setUp() {
        category = new Category(CATEGORY_NAME);
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .memberRole(MemberRole.USER)
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
                () -> assertThat(member.memberRole()).isEqualTo(MemberRole.USER)
        );
    }

    @DisplayName("Member 인스턴스의 MemberCategory 값 추가 테스트")
    @Test
    void addMemberCategory_test() {
        final MemberCategory memberCategory = MemberCategory.createMemberCategory(member, category);
        member.addMemberCategory(memberCategory);

        assertThat(member.memberCategories().size()).isEqualTo(1);
    }

}