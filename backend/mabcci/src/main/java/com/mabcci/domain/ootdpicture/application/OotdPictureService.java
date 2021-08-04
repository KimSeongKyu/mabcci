package com.mabcci.domain.ootdpicture.application;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.ootdpicture.dto.OotdPictureRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdPictureService {

    private final OotdPictureRepository ootdPictureRepository;

    public OotdPictureService(final OotdPictureRepository ootdPictureRepository) {
        this.ootdPictureRepository = ootdPictureRepository;
    }

    @Transactional
    public void registerOotdPictures(final OotdPictureRegisterRequest ootdPictureRegisterRequest) {
        final Ootd ootd = ootdPictureRegisterRequest.getOotd();
        ootdPictureRegisterRequest.getPictures()
                .stream()
                .map(picture -> OotdPicture.builder()
                        .picture(picture)
                        .ootd(ootd)
                        .build())
                .forEachOrdered(ootdPictureRepository::save);
    }
}

