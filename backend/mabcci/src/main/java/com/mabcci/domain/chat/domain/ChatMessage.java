package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;
import javax.persistence.*;

@Entity
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id", updatable = false)
    private Long id;

    @Column(name = "chat_message_message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected ChatMessage() { }

    public ChatMessage(final String message, final ChatRoom chatRoom, final Member member) {
        this.message = message;
        this.chatRoom = chatRoom;
        this.member = member;
    }

    public Long id() {
        return id;
    }

    public String message() {
        return message;
    }

    public ChatRoom chatRoom() {
        return chatRoom;
    }

    public Member member() {
        return member;
    }

}
