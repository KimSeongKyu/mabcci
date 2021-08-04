package com.mabcci.domain.ootd.application;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdService {

    private final OotdRepository ootdRepository;

    public OotdService(final OotdRepository ootdRepository) {
        this.ootdRepository = ootdRepository;
    }

    @Transactional
    public void saveOotd(final Ootd ootd) {
        ootdRepository.save(ootd);
    }
}
