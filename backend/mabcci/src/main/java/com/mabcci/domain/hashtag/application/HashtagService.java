package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.HashtagRegisterRequest;
import com.mabcci.domain.hashtag.dto.HashtagRegisterResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagService(final HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    @Transactional
    public HashtagRegisterResponse saveHashtags(final HashtagRegisterRequest hashtagRegisterRequest) {
        final List<String> hashtagNames = hashtagRegisterRequest.getNames();
        return new HashtagRegisterResponse(hashtagNames.stream()
                .map(name -> hashtagRepository.findByName(name)
                        .orElse(hashtagRepository.save(Hashtag.builder()
                                .name(name)
                                .build())))
                .collect(toList()));
    }
}
