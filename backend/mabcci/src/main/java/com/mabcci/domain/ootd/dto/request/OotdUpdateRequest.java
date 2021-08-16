package com.mabcci.domain.ootd.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public final class OotdUpdateRequest {

    @NotEmpty @JsonProperty("content")
    private String content;

    @JsonProperty("top")
    private String top;

    @JsonProperty("bottom")
    private String bottom;

    @JsonProperty("shoes")
    private String shoes;

    @JsonProperty("accessory")
    private String accessory;

    private OotdUpdateRequest() {
    }

    public OotdUpdateRequest(final String content, final String top, final String bottom, final String shoes,
                             final String accessory) {
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }

    public String content() {
        return content;
    }

    public String top() {
        return top;
    }

    public String bottom() {
        return bottom;
    }

    public String shoes() {
        return shoes;
    }

    public String accessory() {
        return accessory;
    }
}
