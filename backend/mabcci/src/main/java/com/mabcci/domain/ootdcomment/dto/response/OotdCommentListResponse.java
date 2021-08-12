package com.mabcci.domain.ootdcomment.dto.response;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdCommentListResponse {

    @NotNull
    private List<OotdCommentResponse> ootdCommentResponses;

    private OotdCommentListResponse() {
    }

    public OotdCommentListResponse(final List<OotdCommentResponse> ootdCommentResponses) {
        this.ootdCommentResponses = ootdCommentResponses;
    }

    public final List<OotdCommentResponse> getOotdCommentResponses() {
        return ootdCommentResponses;
    }
}
