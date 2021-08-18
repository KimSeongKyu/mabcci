package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;
import javax.persistence.*;

@Entity
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id", updatable = false)
    private Long id;

    @Column(name = "chat_message_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected ChatMessage() { }

    public ChatMessage(final String content, final ChatRoom chatRoom, final Member member) {
        this.content = content;
        this.chatRoom = chatRoom;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public Member getMember() {
        return member;
    }

}
