package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

public final class MemberByMemberRoleResponse {

    @Valid
    private Nickname nickname;

    private String picture;

    @Size(min = 1)
    private Set<MemberCategory> categories;

    public static final MemberByMemberRoleResponse createMemberByMemberRoleResponse(final Member member) {
        return new MemberByMemberRoleResponse(member.nickname(), member.picture(), member.memberCategories());
    }

    MemberByMemberRoleResponse() {
    }

    private MemberByMemberRoleResponse(@Valid final Nickname nickname, final String picture, final Set<MemberCategory> categories) {
        this.nickname = nickname;
        this.picture = picture;
        this.categories = categories;
    }

    public Nickname getNickName() {
        return nickname;
    }

    public String getPicture() {
        return picture;
    }

    public Set<MemberCategory> getCategories() {
        return categories;
    }
}
