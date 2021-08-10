package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.HashtagSaveRequest;
import com.mabcci.domain.hashtag.dto.HashtagSaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagService(final HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    @Transactional
    public HashtagSaveResponse saveHashtags(final HashtagSaveRequest hashtagSaveRequest) {
        final List<String> hashtagNames = hashtagSaveRequest.names();
        return new HashtagSaveResponse(mapToHashTag(hashtagNames));
    }

    private List<Hashtag> mapToHashTag(final List<String> hashtagNames) {
        return hashtagNames.stream()
                .map(this::getOrGenerateHashtag)
                .collect(toList());
    }

    private Hashtag getOrGenerateHashtag(final String name) {
        final Optional<Hashtag> hashtag = hashtagRepository.findByName(name);
        if (hashtag.isPresent()) {
            return hashtag.get();
        }
        return hashtagRepository.save(buildHashTag(name));
    }

    private Hashtag buildHashTag(final String name) {
        return Hashtag.builder()
                .name(name)
                .build();
    }

}
