package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.chat.domain.ChatRoom;
import com.mabcci.domain.chat.domain.Chatting;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Nickname;

public class ChattingRoomListResponse {

    @JsonProperty("roomId")
    private String roomId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("picture")
    private String picture;

    public static ChattingRoomListResponse ofChatting(final Chatting chatting) {
        final ChatRoom chatRoom = chatting.chatRoom();
        final Member mabcci = chatting.mabcci();
        final Nickname mabcciNickname = mabcci.nickname();
        return new ChattingRoomListResponse(chatRoom.id(), mabcciNickname.nickname(), mabcci.picture());
    }

    private ChattingRoomListResponse(final String roomId, final String nickname, final String picture) {
        this.roomId = roomId;
        this.nickname = nickname;
        this.picture = picture;
    }

    public String roomId() {
        return roomId;
    }

    public String nickname() {
        return nickname;
    }

    public String picture() {
        return picture;
    }

}
