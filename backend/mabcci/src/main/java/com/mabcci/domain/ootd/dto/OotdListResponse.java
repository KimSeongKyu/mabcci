package com.mabcci.domain.ootd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public final class OotdListResponse {

    @NotEmpty
    @JsonProperty("ootdList")
    private List<OotdResponse> ootdResponses;

    @PositiveOrZero
    private int totalPages;

    private OotdListResponse() {
    }

    public OotdListResponse(final List<OotdResponse> ootdResponses, final int totalPages) {
        this.ootdResponses = ootdResponses;
        this.totalPages = totalPages;
    }

    public final List<OotdResponse> getOotdResponses() {
        return ootdResponses;
    }

    public final int getTotalPages() {
        return totalPages;
    }
}
