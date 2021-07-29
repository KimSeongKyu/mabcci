package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberSpecsTest {

    @DisplayName("MemberSpecs 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final MemberSpecs memberSpecs = memberSpecs(170, 68, 255, BodyForm.SLENDER);

        assertAll(
                () -> assertThat(memberSpecs).isNotNull(),
                () -> assertThat(memberSpecs).isExactlyInstanceOf(MemberSpecs.class)
        );
    }

    @DisplayName("MemberSpecs 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final MemberSpecs memberSpecs = new MemberSpecs();

        assertAll(
                () -> assertThat(memberSpecs).isNotNull(),
                () -> assertThat(memberSpecs).isExactlyInstanceOf(MemberSpecs.class)
        );
    }

    private MemberSpecs memberSpecs(int height, int weight, int footSize, BodyForm bodyForm) {
        return MemberSpecs.Build()
                .height(height)
                .weight(weight)
                .footSize(footSize)
                .form(bodyForm)
                .build();
    }

}