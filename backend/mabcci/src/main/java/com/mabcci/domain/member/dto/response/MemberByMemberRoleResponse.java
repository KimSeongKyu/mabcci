package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

public final class MemberByMemberRoleResponse {

    @Valid
    private Nickname nickname;

    private String picture;

    @Size(min = 1)
    private Set<Category> categories;

    public static final MemberByMemberRoleResponse createMemberByMemberRoleResponse(@Valid final Nickname nickname, final String picture, final Set<Category> categories) {
        return new MemberByMemberRoleResponse(nickname, picture, categories);
    }

    MemberByMemberRoleResponse() {
    }

    private MemberByMemberRoleResponse(@Valid final Nickname nickname, final String picture, final Set<Category> categories) {
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

    public Set<Category> getCategories() {
        return categories;
    }
}
