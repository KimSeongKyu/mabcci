package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotEmpty;

public final class OotdUpdateRequest {

    @NotEmpty
    private String content;

    private String top;
    private String bottom;
    private String shoes;
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
}
