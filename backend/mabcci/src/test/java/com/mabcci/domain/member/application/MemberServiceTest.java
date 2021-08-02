package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.model.Password;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private static final HashSet<String> CATEGORIES = new HashSet<>(Arrays.asList("categoryName"));

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private MemberService memberService;

    @InjectMocks
    private MemberJoinService memberJoinService;

    @DisplayName("MemberService 인스턴스 join() 기능 테스트")
    @Test
    void join_test() {
        given(memberRepository.save(any())).willReturn(MEMBER);
        given(categoryRepository.findByCategoryName(any())).willReturn(Optional.ofNullable(CATEGORY));
        final MemberResponseDto memberResponseDto = memberJoinService.join(MEMBER, CATEGORIES);

        assertAll(
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(MEMBER.email()),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(MEMBER.nickname()),
                () -> assertThat(memberResponseDto.getGender()).isEqualTo(MEMBER.gender()),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MEMBER.role())
        );
    }

    @DisplayName("MemberService 인스턴스 delete() 기능 테스트")
    @Test
    void delete_test() {
        doReturn(Optional.ofNullable(MEMBER)).when(memberRepository).findByNicknameAndPassword(any(), any());
        doNothing().when(memberRepository).delete(any());

        memberService.delete(MEMBER.nickname(), Password.of("password"));

        verify(memberRepository, times(1)).delete(any());
    }


}