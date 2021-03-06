package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.*;
import com.mabcci.domain.member.dto.response.MemberFindSimpleResponse;
import com.mabcci.domain.member.dto.response.MemberFindSimpleResponses;
import com.mabcci.domain.member.dto.response.MemberListResponse;
import com.mabcci.domain.member.dto.response.MemberFindByNickNameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.domain.member.domain.MemberSpecsTest.*;
import static com.mabcci.domain.member.domain.MemberSpecsTest.BODY_TYPE;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberFindServiceTest {

    public static final String CATEGORY_NAME = "category";

    @Mock private MemberRepository memberRepository;
    @InjectMocks private MemberFindService memberFindService;
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
                .picture(PICTURE)
                .gender(MAN)
                .memberRole(USER)
                .memberSpecs(memberSpecs)
                .build();
        category = new Category(MemberFindServiceTest.CATEGORY_NAME);
    }

    @DisplayName("MemberFindService ???????????? findByNickname() ?????? ?????????")
    @Test
    void find_by_nickname_test() {
        given(memberRepository.findByNickName(any())).willReturn(Optional.ofNullable(member));
        final Member findMember = memberFindService.findByNickname(member.nickname());
        final MemberFindByNickNameResponse memberFindByNickNameResponse = MemberFindByNickNameResponse.ofMember(findMember);

        assertAll(
                () -> assertThat(memberFindByNickNameResponse.email()).isEqualTo(member.email()),
                () -> assertThat(memberFindByNickNameResponse.nickname()).isEqualTo(member.nickname()),
                () -> assertThat(memberFindByNickNameResponse.gender()).isEqualTo(member.gender()),
                () -> assertThat(memberFindByNickNameResponse.role()).isEqualTo(member.memberRole())
        );
    }

    @DisplayName("MemberFindService ???????????? findByNicknameContains() ?????? ?????????")
    @Test
    void find_by_nickname_contains_test() {
        given(memberRepository.findByNicknameContains(any())).willReturn(List.of(member));
        final MemberFindSimpleResponses memberFindSimpleResponses =
                memberFindService.findByNicknameContains(NICKNAME);

        final MemberFindSimpleResponse memberFindSimpleResponse =
                memberFindSimpleResponses.members().get(0);

        verify(memberRepository, times(1)).findByNicknameContains(any());
        assertThat(memberFindSimpleResponse.nickname()).isEqualTo(NICKNAME);
        assertThat(memberFindSimpleResponse.picture()).isEqualTo(PICTURE);
    }

    @DisplayName("MemberService ???????????? findAll() ?????? ?????????")
    @Test
    void find_all_test() {
        final List list = new ArrayList(Arrays.asList(member));
        given(memberRepository.findAll()).willReturn((list));
        final List<MemberListResponse> memberListResponses = memberFindService.findAll();
        final MemberListResponse memberListResponse = memberListResponses.get(0);

        assertAll(
                () -> assertThat(memberListResponses.size()).isEqualTo(1),
                () -> assertThat(memberListResponse.getEmail()).isEqualTo(member.email()),
                () -> assertThat(memberListResponse.getNickname()).isEqualTo(member.nickname()),
                () -> assertThat(memberListResponse.getGender()).isEqualTo(member.gender()),
                () -> assertThat(memberListResponse.getRole()).isEqualTo(member.memberRole())
        );
    }

    @DisplayName("MemberFindService ???????????? findPopularMabccies() ?????? ?????????")
    @Test
    void find_popular_mabccies_test() {
        final List list = new ArrayList(Arrays.asList(member));
        given(memberRepository.findAllByIsPopularMabcci()).willReturn((list));
        final MemberFindSimpleResponses memberFindSimpleResponses = memberFindService.findPopularMabccies();
        final MemberFindSimpleResponse memberFindSimpleResponse = memberFindSimpleResponses.members().get(0);

        assertAll(
                () -> assertThat(memberFindSimpleResponse.nickname()).isEqualTo(member.nickname()),
                () -> assertThat(memberFindSimpleResponse.picture()).isEqualTo(member.picture())
        );
    }

}