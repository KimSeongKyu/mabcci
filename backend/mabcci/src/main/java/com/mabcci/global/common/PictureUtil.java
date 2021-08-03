package com.mabcci.global.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PictureUtil {

    private final static String TIME_FORMAT = "yyyyMMdd";
    private final static String IMAGES_DIRECTORY_NAME = "images";

    public List<Picture> toEntities(final List<MultipartFile> pictures) {
        List<Picture> entities = new ArrayList<>();

        if (!pictures.isEmpty()) {
            entities.add(new Picture());
        }

        return entities;
    }

    public String makeDirectoryName() {
        final String todayFormedToTimeFormat = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return IMAGES_DIRECTORY_NAME + File.separator + todayFormedToTimeFormat;
    }
}
