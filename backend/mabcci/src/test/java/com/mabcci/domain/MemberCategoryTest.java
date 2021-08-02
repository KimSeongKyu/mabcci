package com.mabcci.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberCategoryTest {

    @DisplayName("MemberCategory 기본 생성자 테스트")
    @Test
    void default_constructor_test() {
        final MemberCategory memberCategory = new MemberCategory();

        assertAll(
                () -> assertThat(memberCategory).isNotNull(),
                () -> assertThat(memberCategory).isExactlyInstanceOf(MemberCategory.class)
        );
    }

}