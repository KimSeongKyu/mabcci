package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.Chatting;
import com.mabcci.domain.member.domain.Member;

import java.util.Set;
import java.util.stream.Collectors;

public class ChattingRoomResponse {

    @JsonProperty("participant")
    private Set<ChattingParticipant> participants;

    public ChattingRoomResponse(final ChatRoom chatRoom) {
        this(chatRoom.getChattings().stream()
                .map(Chatting::getMember)
                .map(ChattingParticipant::new)
                .collect(Collectors.toSet()));
    }

    public ChattingRoomResponse(final Set<ChattingParticipant> participants) {
        this.participants = participants;
    }

}
