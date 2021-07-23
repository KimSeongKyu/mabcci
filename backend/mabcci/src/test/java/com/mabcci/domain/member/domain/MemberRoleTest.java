package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRoleTest {

    @DisplayName("MemberRole 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(MemberRole.USER).isExactlyInstanceOf(MemberRole.class),
                () -> assertThat(MemberRole.MABCCI).isExactlyInstanceOf(MemberRole.class),
                () -> assertThat(MemberRole.ADMIN).isExactlyInstanceOf(MemberRole.class)
        );
    }




}