package com.mabcci.domain.ootdhashtag.application;

import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mabcci.domain.ootdhashtag.domain.OotdHashtagTest.OOTD_HASHTAG;
import static com.mabcci.domain.ootdhashtag.dto.OotdHashtagSaveRequestTest.OOTD_HASHTAG_SAVE_REQUEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdHashtagServiceTest {

    @InjectMocks
    private OotdHashtagService ootdHashtagService;

    @Mock
    private OotdHashtagRepository ootdHashtagRepository;

    @DisplayName("OotdHashtagService 인스턴스 OotdHashtags 저장 테스트")
    @Test
    void save_ootd_hashtags_test() {
        doReturn(OOTD_HASHTAG).when(ootdHashtagRepository).save(any());

        ootdHashtagService.saveOotdHashtags(OOTD_HASHTAG_SAVE_REQUEST);

        verify(ootdHashtagRepository, times(OOTD_HASHTAG_SAVE_REQUEST.getHashtags().size())).save(any());
    }
}
