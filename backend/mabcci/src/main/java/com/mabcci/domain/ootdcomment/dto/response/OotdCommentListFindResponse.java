package com.mabcci.domain.ootdcomment.dto.response;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdCommentListFindResponse {

    @NotNull
    private List<OotdCommentFindResponse> ootdCommentFindResponse;

    private OotdCommentListFindResponse() {
    }

    public OotdCommentListFindResponse(final List<OotdCommentFindResponse> ootdCommentFindResponse) {
        this.ootdCommentFindResponse = ootdCommentFindResponse;
    }

    public final List<OotdCommentFindResponse> getOotdCommentResponses() {
        return ootdCommentFindResponse;
    }
}
