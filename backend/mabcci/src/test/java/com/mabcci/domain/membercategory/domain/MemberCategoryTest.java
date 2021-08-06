package com.mabcci.domain.membercategory.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.application.MemberFindServiceTest.CATEGORY_NAME;
import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MemberCategoryTest {

    private Member member;
    private Category category;

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
        category = new Category(CATEGORY_NAME);
    }

    @DisplayName("MemberCategory 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final MemberCategory memberCategory = new MemberCategory();

        assertAll(
                () -> assertThat(memberCategory).isNotNull(),
                () -> assertThat(memberCategory).isExactlyInstanceOf(MemberCategory.class)
        );
    }

    @DisplayName("MemberCategory 정적 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_method_test() {
        final MemberCategory memberCategory = MemberCategory.createMemberCategory(member, category);

        assertAll(
                () -> assertThat(memberCategory).isNotNull(),
                () -> assertThat(memberCategory).isExactlyInstanceOf(MemberCategory.class)
        );
    }

}