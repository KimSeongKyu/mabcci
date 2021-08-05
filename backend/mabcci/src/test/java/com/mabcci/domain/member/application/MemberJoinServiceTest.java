package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mabcci.domain.category.domain.CategoryTest.CATEGORY;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberJoinServiceTest {

    private static final HashSet<String> CATEGORIES = new HashSet<>(Arrays.asList("categoryName"));

    @Mock private MemberRepository memberRepository;
    @Mock private CategoryRepository categoryRepository;
    @InjectMocks private MemberJoinService memberJoinService;

    @DisplayName("MemberJoinService 인스턴스 join() 기능 테스트")
    @Test
    void join_test() {
        given(memberRepository.save(any())).willReturn(MEMBER);
        given(categoryRepository.findByCategoryName(any())).willReturn(Optional.ofNullable(CATEGORY));

        final Member member = memberJoinService.join(MEMBER, CATEGORIES);
        final MemberResponse memberResponse = new MemberResponse(member);

        assertAll(
                () -> assertThat(memberResponse.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponse.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponse.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponse.getRole()).isEqualTo(MEMBER.MemberRole())
        );
    }


}