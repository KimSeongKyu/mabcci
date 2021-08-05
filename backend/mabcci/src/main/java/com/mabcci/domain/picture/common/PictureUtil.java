package com.mabcci.domain.picture.common;

import com.mabcci.domain.picture.domain.Picture;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PictureUtil {

    private final static String TIME_FORMAT = "yyyyMMdd";
    private final static String IMAGES_DIRECTORY_NAME = "images";
    private final static String PNG_FILE_EXTENSION = ".png";
    private final static String JPG_FILE_EXTENSION = ".jpg";
    private final static String BASE_PATH = "";

    public Picture savePicture(final MultipartFile picture, final String directoryName) {
        final String fileName = makeFileName(makeFileExtension(picture.getContentType()));
        final File file = new File(makeAbsolutePath() + directoryName + File.separator + fileName);

        file.setWritable(false);
        file.setReadable(true);

        try {
            picture.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Picture(directoryName, fileName);
    }

    public void makeDirectory(final String directoryName) {
        final File file = new File(directoryName);
        file.mkdirs();
    }

    public String makeAbsolutePath() {
        return new File(BASE_PATH).getAbsolutePath() + File.separator + File.separator;
    }

    public String makeDirectoryName() {
        final String todayFormedToTimeFormat = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return IMAGES_DIRECTORY_NAME + File.separator + todayFormedToTimeFormat;
    }

    public String makeFileName(final String fileExtension) {
        return System.nanoTime() + fileExtension;
    }

    public String makeFileExtension(final String contentType) {
        if (contentType.contains(MediaType.IMAGE_PNG_VALUE)) {
            return PNG_FILE_EXTENSION;
        }
        return JPG_FILE_EXTENSION;
    }
}
