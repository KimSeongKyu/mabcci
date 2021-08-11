package com.mabcci.domain.picture.common;

import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.domain.PictureType;
import org.springframework.beans.factory.annotation.Value;
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
    private final static String PNG_FILE_EXTENSION = ".png";
    private final static String JPG_FILE_EXTENSION = ".jpg";
    private final static String URL_SEPARATOR = "/";

    @Value("${path.upload}")
    private String baseUrl;

    @Value("${path.real}")
    private String baseDirectory;

    public Picture savePicture(final MultipartFile picture, final String directoryName) {
        final String fileName = makeFileName(makeFileExtension(picture.getContentType()));
        final File file = new File(directoryName + File.separator + fileName);

        file.setWritable(false);
        file.setReadable(true);

        try {
            picture.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Picture(mapDirectoryNameToUrl(directoryName), fileName);
    }

    public String makeDirectory(final PictureType pictureType) {
        final String directoryName = makeDirectoryName(pictureType);
        final File file = new File(directoryName);
        file.mkdirs();
        return directoryName;
    }

    public String makeDirectoryName(final PictureType pictureType) {
        final String todayFormedToTimeFormat = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        return baseDirectory + File.separator + pictureType.type() + File.separator + todayFormedToTimeFormat;
    }

    public String mapDirectoryNameToUrl(final String directoryName) {
        return directoryName.replace(baseDirectory, baseUrl)
                .replace(File.separator, URL_SEPARATOR);
    }

    public String mapUrlToDirectoryName(final String url) {
        return url.replace(baseUrl, baseDirectory)
                .replace(URL_SEPARATOR, File.separator);
    }

    public String makeFileName(final String fileExtension) {
        return System.nanoTime() + fileExtension;
    }

    public String makeFileExtension(final String contentType) {
        System.out.println("contentType: "+ contentType);
        if (contentType.contains(MediaType.IMAGE_PNG_VALUE)) {
            return PNG_FILE_EXTENSION;
        }
        return JPG_FILE_EXTENSION;
    }

    public void deletePicture(final Picture picture) {
        final String path = mapUrlToDirectoryName(picture.path());
        new File(path).delete();
    }
}
