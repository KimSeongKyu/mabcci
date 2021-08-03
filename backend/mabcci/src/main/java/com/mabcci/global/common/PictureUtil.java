package com.mabcci.global.common;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureUtil {

    public PictureUtil() {
    }

    public List<Picture> toEntities(final List<MultipartFile> pictures) {
        List<Picture> entities = new ArrayList<>();

        if(!pictures.isEmpty()) {
            entities.add(new Picture());
        }

        return entities;
    }
}
