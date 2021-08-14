package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.HashtagSaveResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.hashtag.dto.HashtagSaveRequestTest.HASHTAG_SAVE_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @InjectMocks private HashtagService hashtagService;
    @Mock private HashtagRepository hashtagRepository;

    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
    }

    @DisplayName("HashtagService 인스턴스 Hashtag 저장 테스트")
    @Test
    void save_hashtags_test() {
        doReturn(Optional.empty()).when(hashtagRepository).findByName(any());
        doReturn(hashtag).when(hashtagRepository).save(any());

        final HashtagSaveResponse hashtagSaveResponse = hashtagService.saveHashtags(HASHTAG_SAVE_REQUEST);
        final int numberOfRequestNames = HASHTAG_SAVE_REQUEST.names().size();

        verify(hashtagRepository, times(numberOfRequestNames)).findByName(any());
        verify(hashtagRepository, times(numberOfRequestNames)).save(any());

        assertAll(
                () -> assertThat(hashtagSaveResponse).isNotNull(),
                () -> assertThat(hashtagSaveResponse.hashtags().size()).isEqualTo(numberOfRequestNames)
        );
    }
}
