package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.dto.response.MemberByNickNameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mabcci.domain.member.application.MemberFindServiceTest.CATEGORY_NAME;
import static com.mabcci.domain.member.domain.MemberTest.*;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberJoinServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private MemberJoinService memberJoinService;

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


    @DisplayName("MemberJoinService 인스턴스 join() 기능 테스트")
    @Test
    void join_test() {
        given(memberRepository.save(any())).willReturn(member);
        given(categoryRepository.findByCategoryName(any())).willReturn(Optional.ofNullable(category));
        final Set<String> categoryNames = new HashSet<>(Arrays.asList("category"));
        final Member findMember = memberJoinService.join(member, categoryNames);
        final MemberByNickNameResponse memberByNickNameResponse = new MemberByNickNameResponse(findMember);

        assertAll(
                () -> assertThat(memberByNickNameResponse.getEmail()).isEqualTo(member.email()),
                () -> assertThat(memberByNickNameResponse.getNickname()).isEqualTo(member.nickname()),
                () -> assertThat(memberByNickNameResponse.getGender()).isEqualTo(member.gender()),
                () -> assertThat(memberByNickNameResponse.getRole()).isEqualTo(member.memberRole())
        );
    }


}