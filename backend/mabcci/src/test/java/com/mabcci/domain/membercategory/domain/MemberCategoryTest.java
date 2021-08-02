package com.mabcci.domain.membercategory.domain;

import com.mabcci.domain.membercategory.domain.MemberCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.category.domain.CategoryTest.CATEGORY;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberCategoryTest {

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
        final MemberCategory memberCategory = MemberCategory.createMemberCategory(MEMBER, CATEGORY);

        assertAll(
                () -> assertThat(memberCategory).isNotNull(),
                () -> assertThat(memberCategory).isExactlyInstanceOf(MemberCategory.class)
        );
    }

}