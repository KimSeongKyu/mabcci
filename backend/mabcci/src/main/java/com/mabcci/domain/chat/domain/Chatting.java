package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class Chatting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatting_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "proposal_id")
    private Member proposal;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "mabcci_id")
    private Member mabcci;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    protected Chatting() {
    }

    public Chatting(final Member proposal, final Member mabcci, final ChatRoom chatRoom) {
        this.proposal = proposal;
        this.mabcci = mabcci;
        this.chatRoom = chatRoom;
    }

    public Long id() {
        return id;
    }

    public Member proposal() {
        return proposal;
    }

    public Member mabcci() {
        return mabcci;
    }

    public ChatRoom chatRoom() {
        return chatRoom;
    }

    public boolean isContainSameMember(final Member other) {
        return this.proposal.equals(other);
    }

}
