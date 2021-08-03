package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.global.common.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberDeleteServiceTest {

    @Mock private MemberRepository memberRepository;
    @InjectMocks private MemberDeleteService memberDeleteService;

    @DisplayName("MemberDeleteService 인스턴스 delete() 기능 테스트")
    @Test
    void delete_test() {
        doReturn(Optional.ofNullable(MEMBER)).when(memberRepository).findByNicknameAndPassword(any(), any());
        doNothing().when(memberRepository).delete(any());

        memberDeleteService.delete(MEMBER.nickname(), Password.of("password"));

        verify(memberRepository, times(1)).delete(any());
    }

}
