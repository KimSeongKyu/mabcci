package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemberSpecsTest {

    public static final int HEIGHT = 170;
    public static final int WEIGHT = 68;
    public static final int FOOT_SIZE = 255;
    public static final BodyType BODY_TYPE = BodyType.TRIANGLE;


    @DisplayName("MemberSpecs 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final MemberSpecs memberSpecs = memberSpecs(HEIGHT, WEIGHT, FOOT_SIZE, BodyType.TRIANGLE);

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

    @DisplayName("MemberSpecs 인스턴스 getter 기능 테스트")
    @Test
    void getter_test() {
        final MemberSpecs memberSpecs = memberSpecs(HEIGHT, WEIGHT, FOOT_SIZE, BodyType.TRIANGLE);

        assertAll(
                () -> assertThat(memberSpecs.height()).isEqualTo(HEIGHT),
                () -> assertThat(memberSpecs.weight()).isEqualTo(WEIGHT),
                () -> assertThat(memberSpecs.footSize()).isEqualTo(FOOT_SIZE),
                () -> assertThat(memberSpecs.bodyType()).isEqualTo(BodyType.TRIANGLE)
        );
    }


    private MemberSpecs memberSpecs(int height, int weight, int footSize, BodyType bodyType) {
        return MemberSpecs.Builder()
                .height(height)
                .weight(weight)
                .footSize(footSize)
                .form(bodyType)
                .build();
    }

}