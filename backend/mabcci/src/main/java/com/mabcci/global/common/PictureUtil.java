package com.mabcci.global.common;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class PictureUtil {

    private final static String TIME_FORMAT = "yyyyMMdd";
    private final static String IMAGES_DIRECTORY_NAME = "images";
    private final static String PNG_FILE_EXTENSION = ".png";
    private final static String JPG_FILE_EXTENSION = ".jpg";

    public List<Picture> toEntities(final List<MultipartFile> pictures) {
        return pictures.stream()
                .map(picture -> new Picture(makeDirectoryName(),
                        makeFileName(makeOriginalFileExtension(picture.getContentType()))))
                .collect(toList());
    }

    public Picture saveFile(final MultipartFile picture, final String directoryName) throws Exception {
        final String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        final String fileName = makeFileName(makeOriginalFileExtension(picture.getContentType()));
        final File file = new File(absolutePath + directoryName + File.separator + fileName);

        file.setWritable(false);
        file.setReadable(true);

        picture.transferTo(file);

        return new Picture(directoryName, fileName);
    }

    public boolean makeDirectory(final String directoryName) {
        final File file = new File(directoryName);
        return file.mkdirs();
    }

    public String makeDirectoryName() {
        final String todayFormedToTimeFormat = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return IMAGES_DIRECTORY_NAME + File.separator + todayFormedToTimeFormat;
    }

    public String makeFileName(final String fileExtension) {
        return System.nanoTime() + fileExtension;
    }

    public String makeOriginalFileExtension(final String contentType) {
        if (contentType.contains(MediaType.IMAGE_PNG_VALUE)) {
            return PNG_FILE_EXTENSION;
        }
        return JPG_FILE_EXTENSION;
    }
}
