package com.mabcci.domain.member.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.response.MemberByMemberRoleResponse;
import com.mabcci.domain.member.dto.response.MemberListResponse;
import com.mabcci.domain.member.dto.response.MemberByNickNameResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberFindServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberFindService memberFindService;

    @DisplayName("MemberFindService 인스턴스 findByNickname() 기능 테스트")
    @Test
    void findByNickname_test() {
        given(memberRepository.findByNickName(any())).willReturn(Optional.ofNullable(MEMBER));
        final Member member = memberFindService.findByNickname(MEMBER.nickname());
        final MemberByNickNameResponse memberByNickNameResponse = new MemberByNickNameResponse(member);

        assertAll(
                () -> assertThat(memberByNickNameResponse.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberByNickNameResponse.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberByNickNameResponse.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberByNickNameResponse.getRole()).isEqualTo(MEMBER.memberRole())
        );
    }

    @DisplayName("MemberFindService 인스턴스 findByMemberRole() 기능 테스트")
    @Test
    void findByMemberRole_test() {
        given(memberRepository.findByMemberRole(any())).willReturn(Optional.ofNullable(MEMBER));
        final Member member = memberFindService.findByMemberRole(MEMBER.memberRole());
        final MemberByMemberRoleResponse response =
                MemberByMemberRoleResponse.createMemberByMemberRoleResponse(member.nickname(), member.picture(), member.categories());

        assertAll(
                () -> assertThat(response.getNickName()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(response.getPicture()).isEqualTo(MEMBER.picture()),
                () -> assertThat(response.getCategories()).isEqualTo(MEMBER.categories())
        );
    }


    @DisplayName("MemberService 인스턴스 findAll() 기능 테스트")
    @Test
    void findAll_test() {
        final List list = new ArrayList(Arrays.asList(MEMBER));
        given(memberRepository.findAll()).willReturn((list));
        final List<MemberListResponse> memberListResponses = memberFindService.findAll();
        final MemberListResponse memberListResponse = memberListResponses.get(0);

        assertAll(
                () -> assertThat(memberListResponses.size()).isEqualTo(1),
                () -> assertThat(memberListResponse.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberListResponse.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberListResponse.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberListResponse.getRole()).isEqualTo(MEMBER.memberRole())
        );
    }

}