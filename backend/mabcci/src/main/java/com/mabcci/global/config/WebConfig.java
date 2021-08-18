package com.mabcci.global.config;

import com.mabcci.domain.ootd.util.StringToOotdFilterConverter;
import com.mabcci.domain.proposal.util.StringToProposalConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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
        List<String> folders = Arrays.asList("/profile/", "/ootd/", "/proposal/");
        folders.stream()
                .forEach(folder -> registry.addResourceHandler(uploadPath + folder + "**")
                        .addResourceLocations(resourcePath + folder)
                );
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToOotdFilterConverter());
        registry.addConverter(new StringToProposalConverter());
    }
}
