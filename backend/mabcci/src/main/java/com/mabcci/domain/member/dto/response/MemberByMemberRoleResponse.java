package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public final class MemberByMemberRoleResponse {

    @Valid
    private Nickname nickname;

    private String picture;

    @Size(min = 1)
    private Set<String> categories;

    public static final MemberByMemberRoleResponse createMemberByMemberRoleResponse(final Member member) {
        return new MemberByMemberRoleResponse(member.nickname(), member.picture(), memberCategoryMapToCategoryNames(member));
    }

    private static Set<String> memberCategoryMapToCategoryNames(final Member member) {
        return member.memberCategories().stream()
                .map(MemberCategory::category)
                .map(Category::categoryName)
                .collect(Collectors.toSet());
    }

    MemberByMemberRoleResponse() {
    }

    private MemberByMemberRoleResponse(@Valid final Nickname nickname, final String picture, final Set<String> categories) {
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

    public Set<String> getCategories() {
        return categories;
    }

}
