package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class Chatting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    protected Chatting() {
    }

    public Chatting(final Member member, final ChatRoom chatRoom) {
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void addChatRoom(final ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

}
