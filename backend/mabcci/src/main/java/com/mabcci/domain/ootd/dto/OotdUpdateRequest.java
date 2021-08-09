package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class OotdUpdateRequest {

    @NotEmpty
    private String content;

    private String top;
    private String bottom;
    private String shoes;
    private String accessory;
    private List<String> hashtags;

    private OotdUpdateRequest() {
    }

    public OotdUpdateRequest(final String content, final String top, final String bottom, final String shoes,
                             final String accessory, final List<String> hashtags) {
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.hashtags = hashtags;
    }

    public String getContent() {
        return content;
    }

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }

    public String getShoes() {
        return shoes;
    }

    public String getAccessory() {
        return accessory;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}
