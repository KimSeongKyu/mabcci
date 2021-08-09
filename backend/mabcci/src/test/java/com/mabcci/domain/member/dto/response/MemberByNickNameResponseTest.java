package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.application.MemberFindServiceTest;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.domain.MemberSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.domain.member.domain.MemberSpecsTest.*;
import static com.mabcci.domain.member.domain.MemberSpecsTest.BODY_TYPE;
import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberByNickNameResponseTest {

    private static final Long ID = 1L;
    private Member member;
    private MemberSpecs memberSpecs;
    private Category category;

    @BeforeEach
    void setUp() {
        memberSpecs = memberSpecs.Builder()
                .height(HEIGHT)
                .weight(WEIGHT)
                .footSize(FOOT_SIZE)
                .form(BODY_TYPE)
                .build();

        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MAN)
                .memberRole(USER)
                .memberSpecs(memberSpecs)
                .build();
        category = new Category(MemberFindServiceTest.CATEGORY_NAME);
        ReflectionTestUtils.setField(member, "id", ID);
    }

    @DisplayName("MemberResponse 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {

        final MemberByNickNameResponse memberByNickNameResponse = new MemberByNickNameResponse(member);

        assertAll(
                () -> assertThat(memberByNickNameResponse).isNotNull(),
                () -> assertThat(memberByNickNameResponse).isExactlyInstanceOf(MemberByNickNameResponse.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final MemberByNickNameResponse memberByNickNameResponse = new MemberByNickNameResponse(member);
        assertAll(
                () -> assertThat(memberByNickNameResponse.getId()).isEqualTo(ID),
                () -> assertThat(memberByNickNameResponse.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(memberByNickNameResponse.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberByNickNameResponse.getRole()).isEqualTo(MemberRole.USER)
        );
    }
}