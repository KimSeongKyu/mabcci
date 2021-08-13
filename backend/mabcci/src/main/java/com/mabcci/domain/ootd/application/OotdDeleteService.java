package com.mabcci.domain.ootd.application;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OotdDeleteService {
    private final OotdRepository ootdRepository;
    private final OotdPictureRepository ootdPictureRepository;
    private final PictureUtil pictureUtil;

    public OotdDeleteService(final OotdRepository ootdRepository,final OotdPictureRepository ootdPictureRepository,
                       final PictureUtil pictureUtil) {
        this.ootdRepository = ootdRepository;
        this.ootdPictureRepository = ootdPictureRepository;
        this.pictureUtil = pictureUtil;
    }
    @Transactional
    public void deleteOotdById(final Long id) {
        final Ootd ootd = getOotdById(id);
        final List<OotdPicture> ootdPictures = getOotdPictures(ootd);

        deletePictureFilesOfOotdPictures(ootdPictures);
        deleteOotd(ootd);
    }

    private void deletePictureFilesOfOotdPictures(final List<OotdPicture> ootdPictures) {
        ootdPictures.stream()
                .forEach(pictureUtil::deletePicture);
    }

    private void deleteOotd(final Ootd ootd) {
        ootdRepository.delete(ootd);
    }

    private List<OotdPicture> getOotdPictures(final Ootd ootd) {
        return ootdPictureRepository.findAllByOotd(ootd);
    }

    private Ootd getOotdById(final Long id) {
        return ootdRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
