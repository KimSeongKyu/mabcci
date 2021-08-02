package com.mabcci.domain.membercategory.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;

@Entity
public class MemberCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static MemberCategory createMemberCategory(final Member member, final Category category) {
        return new MemberCategory(member, category);
    }

    protected MemberCategory() {
    }

    private MemberCategory(final Member member, final Category category) {
        this.member = member;
        this.category = category;
    }

    public void addMember(final Member member) {
        this.member = member;
    }
}
