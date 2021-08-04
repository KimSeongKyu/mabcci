package com.mabcci.domain.ootdhashtag.application;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdhashtag.dto.OotdHashtagRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdHashtagService {

    private final OotdHashtagRepository ootdHashtagRepository;

    public OotdHashtagService(final OotdHashtagRepository ootdHashtagRepository) {
        this.ootdHashtagRepository = ootdHashtagRepository;
    }

    @Transactional
    public void saveOotdHashtags(final OotdHashtagRegisterRequest ootdHashtagRegisterRequest) {
        final Ootd ootd = ootdHashtagRegisterRequest.getOotd();
        ootdHashtagRegisterRequest.getHashtags()
                .stream()
                .map(hashtag -> OotdHashtag.builder()
                        .ootd(ootd)
                        .hashtag(hashtag)
                        .build())
                .forEach(ootdHashtagRepository::save);
    }
}
