package com.mabcci.domain.ootdcomment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdCommentListFindResponse {

    @NotNull
    @JsonProperty("comments")
    private List<OotdCommentFindResponse> ootdCommentFindResponses;

    private OotdCommentListFindResponse() {
    }

    public OotdCommentListFindResponse(final List<OotdCommentFindResponse> ootdCommentFindResponses) {
        this.ootdCommentFindResponses = ootdCommentFindResponses;
    }

    public final List<OotdCommentFindResponse> comments() {
        return ootdCommentFindResponses;
    }
}
