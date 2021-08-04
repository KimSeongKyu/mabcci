package com.mabcci.domain.ootd.application;

import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdServiceTest {

    @InjectMocks
    private OotdService ootdService;

    @Mock
    private OotdRepository ootdRepository;

    @DisplayName("OotdService 인스턴스 ootd 저장 테스트")
    @Test
    void save_ootd_test() {
        doReturn(OOTD).when(ootdRepository).save(any());
        verify(ootdRepository, times(1)).save(any());
    }
}
