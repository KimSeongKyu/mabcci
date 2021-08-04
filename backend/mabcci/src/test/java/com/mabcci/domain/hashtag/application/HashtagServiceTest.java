package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.HashtagRegisterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.hashtag.domain.HashtagTest.HASHTAG;
import static com.mabcci.domain.hashtag.dto.HashtagRegisterRequestTest.HASHTAG_REGISTER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @InjectMocks
    private HashtagService hashtagService;

    @Mock
    private HashtagRepository hashtagRepository;

    @DisplayName("HashtagService 인스턴스 Hashtag 저장 테스트")
    @Test
    void save_hashtags_test() {
        doReturn(Optional.of(HASHTAG)).when(hashtagRepository).findByName(any());
        doReturn(HASHTAG).when(hashtagRepository).save(any());
        final HashtagRegisterResponse hashtagRegisterResponse = hashtagService.saveHashtags(HASHTAG_REGISTER_REQUEST);
        final int numberOfRequestNames = HASHTAG_REGISTER_REQUEST.getNames().size();

        verify(hashtagRepository, times(numberOfRequestNames)).findByName(any());
        verify(hashtagRepository, times(numberOfRequestNames)).save(any());

        assertAll(
                () -> assertThat(hashtagRegisterResponse).isNotNull(),
                () -> assertThat(hashtagRegisterResponse.getHashtags().size()).isEqualTo(numberOfRequestNames)
        );
    }
}
