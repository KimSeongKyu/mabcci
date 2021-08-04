package com.mabcci.domain.follow.domain;

import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class Follow extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following")
    private Member following;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    private Member follower;

    protected Follow() { }

    protected Follow(final FollowBuilder followBuilder) {
        this.following = followBuilder.following;
        this.follower = followBuilder.follower;
    }

    public Long id() {
        return id;
    }

    public Member following() {
        return following;
    }

    public Member follower() {
        return follower;
    }

    public void changeFollowing(final Member following) {
        this.following = following;
    }

    public void changeFollower(final Member follower) {
        this.follower = follower;
    }

    public static FollowBuilder Builder() {
        return new FollowBuilder();
    }

    public static class FollowBuilder {
        private Member following;
        private Member follower;

        private FollowBuilder(){
        }

        public FollowBuilder following(final Member following) {
            this.following = following;
            return this;
        }

        public FollowBuilder follower(final Member follower) {
            this.follower = follower;
            return this;
        }

        public Follow build() {
            return new Follow(this);
        }

    }
}
