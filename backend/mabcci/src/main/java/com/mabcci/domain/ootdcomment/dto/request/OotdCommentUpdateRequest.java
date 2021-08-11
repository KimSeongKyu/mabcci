package com.mabcci.domain.ootdcomment.dto.request;

import javax.validation.constraints.NotEmpty;

public final class OotdCommentUpdateRequest {

    @NotEmpty
    private String content;

    private OotdCommentUpdateRequest() {
    }

    public OotdCommentUpdateRequest(final String content) {
        this.content = content;
    }

    public final String getContent() {
        return content;
    }
}
