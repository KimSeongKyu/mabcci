package com.mabcci.domain.ootd.util;

import com.mabcci.domain.ootd.domain.OotdFilter;
import org.springframework.core.convert.converter.Converter;

public class StringToOotdFilterConverter implements Converter<String, OotdFilter> {
    @Override
    public OotdFilter convert(String value) {
        return OotdFilter.valueOf(value.toUpperCase());
    }
}
