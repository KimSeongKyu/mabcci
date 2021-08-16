package com.mabcci.domain.ootd.application;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdUpdateService {

    private final OotdRepository ootdRepository;

    public OotdUpdateService(final OotdRepository ootdRepository) {
        this.ootdRepository = ootdRepository;
    }

    @Transactional
    public void updateOotd(final Long id, final OotdUpdateRequest ootdUpdateRequest) {
        final Ootd ootd = getOotdById(id);
        ootd.update(ootdUpdateRequest);
    }

    private Ootd getOotdById(final Long id) {
        return ootdRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
