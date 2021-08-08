package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public final class OotdListResponse {

    @NotEmpty
    private List<OotdResponse> ootdResponses;

    @PositiveOrZero
    private Long page;

    private OotdListResponse() {
    }

    public OotdListResponse(final List<OotdResponse> ootdResponses, final Long page) {
        this.ootdResponses = ootdResponses;
        this.page = page;
    }
}
