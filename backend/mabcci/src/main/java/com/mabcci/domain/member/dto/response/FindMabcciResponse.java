package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Nickname;

import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public final class FindMabcciResponse {

    @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("picture")
    private String picture;

    @Size(min = 1)
    @JsonProperty("categories")
    private Set<String> categories;

    public static final FindMabcciResponse ofMember(final Member member) {
        return new FindMabcciResponse(member.nickname(), member.picture(), memberCategoryMapToCategoryNames(member));
    }

    private static final Set<String> memberCategoryMapToCategoryNames(final Member member) {
        return member.memberCategories().stream()
                .map(MemberCategory::category)
                .map(Category::categoryName)
                .collect(Collectors.toSet());
    }

    FindMabcciResponse() {
    }

    private FindMabcciResponse(final Nickname nickname, final String picture, final Set<String> categories) {
        this.nickname = nickname;
        this.picture = picture;
        this.categories = categories;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final String picture() {
        return picture;
    }

    public final Set<String> categories() {
        return categories;
    }

}
