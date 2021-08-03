package com.mabcci.domain.ootdcategory.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.category.domain.CategoryTest.CATEGORY;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OotdCategoryTest {

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
        final Ootd ootd = ootd(MEMBER);
        final Category category = CATEGORY;
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