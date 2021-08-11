package com.mabcci.domain.comment.domain;

import com.mabcci.domain.BaseTimeEntity;
import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@MappedSuperclass
public class Comment extends BaseTimeEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Optional<Comment> parentComment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> childComments = new HashSet<>();

    @NotEmpty
    @Column(name = "comment_content", nullable = false)
    private String content;

    protected Comment() {
    }

    public Comment(final Member member, final Optional<Comment> parentComment, final String content) {
        this.member = member;
        this.parentComment = parentComment;
        this.content = content;
    }

}
