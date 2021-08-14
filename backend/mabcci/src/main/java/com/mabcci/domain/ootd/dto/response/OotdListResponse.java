package com.mabcci.domain.ootd.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public final class OotdListResponse {

    @NotEmpty @JsonProperty("ootdList")
    private List<OotdResponse> ootdResponses;

    @PositiveOrZero @JsonProperty("totalPages")
    private int totalPages;

    private OotdListResponse() {
    }

    public OotdListResponse(final List<OotdResponse> ootdResponses, final int totalPages) {
        this.ootdResponses = ootdResponses;
        this.totalPages = totalPages;
    }

    public final List<OotdResponse> ootdResponses() {
        return ootdResponses;
    }

    public final int totalPages() {
        return totalPages;
    }
}
