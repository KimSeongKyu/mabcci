package com.mabcci.domain.picture.application;

import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.dto.PictureRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PictureService {

    private final PictureUtil pictureUtil;

    public PictureService(final PictureUtil pictureUtil) {
        this.pictureUtil = pictureUtil;
    }

    public List<Picture> savePictures(final PictureRegisterRequest pictureRegisterRequest) {
        final String directoryName = pictureUtil.makeDirectoryName();
        pictureUtil.makeDirectory(directoryName);

        return pictureRegisterRequest.getPictures()
                .stream()
                .map(picture -> pictureUtil.savePicture(picture, directoryName))
                .collect(toList());
    }
}
