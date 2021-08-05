package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdServiceTest {

    @InjectMocks
    private OotdService ootdService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private OotdRepository ootdRepository;

    @DisplayName("OotdService 인스턴스 ootd 저장 테스트")
    @Test
    void save_ootd_test() {
        final OotdSaveRequest ootdSaveRequest = new OotdSaveRequest("닉네임", "내용", "상의", "하의", "신발", "악세사리");
        doReturn(OOTD).when(ootdRepository).save(any());
        doReturn(Optional.of(MEMBER)).when(memberRepository).findByNickname(any());

        ootdService.saveOotd(ootdSaveRequest);

        verify(ootdRepository, times(1)).save(any());
    }
}
