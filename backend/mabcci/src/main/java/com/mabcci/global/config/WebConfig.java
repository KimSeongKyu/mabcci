package com.mabcci.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${path.upload}")
    private String uploadPath;

    @Value("${path.resource}")
    private String resourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        List<String> folders = Arrays.asList("/profile/", "/ootd/", "/board/");
        folders.stream()
                .forEach(folder -> registry.addResourceHandler(uploadPath + folder + "**")
                        .addResourceLocations(resourcePath + folder)
                );
    }
}
