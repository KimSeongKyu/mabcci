package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdService {

    private final OotdRepository ootdRepository;

    public OotdService(final OotdRepository ootdRepository) {
        this.ootdRepository = ootdRepository;
    }

    @Transactional
    public void saveOotd(final OotdRegisterRequest ootdRegisterRequest) {
        final Ootd ootd = Ootd.builder()
                .member(ootdRegisterRequest.getMember())
                .content(ootdRegisterRequest.getContent())
                .top(ootdRegisterRequest.getTop())
                .bottom(ootdRegisterRequest.getBottom())
                .shoes(ootdRegisterRequest.getShoes())
                .accessory(ootdRegisterRequest.getAccessory())
                .build();

        ootdRepository.save(ootd);
    }
}
