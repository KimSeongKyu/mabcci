package com.mabcci.domain.ootdcategory.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
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
import static org.junit.jupiter.api.Assertions.*;

class OotdCategoryTest {

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
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
    }

    @DisplayName("OotdCategory 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdCategory ootdCategory = new OotdCategory();

        assertAll(
                () -> assertThat(ootdCategory).isNotNull(),
                () -> assertThat(ootdCategory).isExactlyInstanceOf(OotdCategory.class)
        );
    }

    @DisplayName("OotdCategory 인스턴스 생성자를 이용한 생성 테스트")
    @Test
    void constructor_test() {
        final Ootd ootd = ootd(member);
        final OotdCategory ootdCategory = OotdCategory.createOotdCategory(ootd, category);

        assertAll(
                () -> assertThat(ootdCategory).isNotNull(),
                () -> assertThat(ootdCategory).isExactlyInstanceOf(OotdCategory.class)
        );
    }

    private Ootd ootd(Member member) {
        return Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
    }

}