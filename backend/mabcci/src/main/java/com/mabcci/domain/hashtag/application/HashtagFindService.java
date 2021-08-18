package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.response.HashtagsFindByNameContainsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class HashtagFindService {

    private final HashtagRepository hashtagRepository;

    public HashtagFindService(final HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public HashtagsFindByNameContainsResponse findByNameContains(final String hashtagName) {
        return new HashtagsFindByNameContainsResponse(getHashtagNames(hashtagName));
    }

    private List<String> getHashtagNames(final String hashtagName) {
        return hashtagRepository.findByNameContains(hashtagName)
                .stream()
                .map(Hashtag::name)
                .collect(toList());
    }
}
