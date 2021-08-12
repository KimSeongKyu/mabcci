package com.mabcci.domain.ootdcomment.dto.response;

import java.util.List;

public final class OotdCommentListResponse {

    private List<OotdCommentResponse> ootdCommentResponses;

    private OotdCommentListResponse() {
    }

    public OotdCommentListResponse(List<OotdCommentResponse> ootdCommentResponses) {
        this.ootdCommentResponses = ootdCommentResponses;
    }
}
