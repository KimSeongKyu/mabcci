package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public final class OotdListResponse {

    @NotEmpty
    private List<OotdResponse> ootdResponses;

    @PositiveOrZero
    private Long totalPages;

    private OotdListResponse() {
    }

    public OotdListResponse(final List<OotdResponse> ootdResponses, final Long totalPages) {
        this.ootdResponses = ootdResponses;
        this.totalPages = totalPages;
    }

    public final List<OotdResponse> getOotdResponses() {
        return ootdResponses;
    }

    public final Long getTotalPages() {
        return totalPages;
    }
}
