package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.application.MemberFindServiceTest;
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
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FindMemberByNickNameResponseTest {

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
    void static_factory_method_test() {

        final FindMemberByNickNameResponse findMemberByNickNameResponse = FindMemberByNickNameResponse.ofMember(member);

        assertAll(
                () -> assertThat(findMemberByNickNameResponse).isNotNull(),
                () -> assertThat(findMemberByNickNameResponse).isExactlyInstanceOf(FindMemberByNickNameResponse.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final FindMemberByNickNameResponse findMemberByNickNameResponse = FindMemberByNickNameResponse.ofMember(member);
        assertAll(
                () -> assertThat(findMemberByNickNameResponse.email()).isEqualTo(EMAIL),
                () -> assertThat(findMemberByNickNameResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(findMemberByNickNameResponse.role()).isEqualTo(MemberRole.USER)
        );
    }
}