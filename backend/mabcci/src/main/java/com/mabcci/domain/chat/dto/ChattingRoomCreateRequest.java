package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

public class ChattingRoomCreateRequest {

    @JsonProperty("mabcci")
    private Nickname nickname;

    public ChattingRoomCreateRequest() {
    }

    public ChattingRoomCreateRequest(final Nickname nickname) {
        this.nickname = nickname;
    }

    public Nickname nickname() {
        return nickname;
    }

}
