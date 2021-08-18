package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.member.domain.*;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.domain.ootd.dto.response.OotdMyPageResponse;
import com.mabcci.global.common.Nickname;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public final class MemberMyPageResponse {

    @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("role")
    private MemberRole role;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("description")
    private String description;

    @JsonProperty("height")
    private int height;

    @JsonProperty("weight")
    private int weight;

    @JsonProperty("footSize")
    private int footSize;

    @JsonProperty("bodyType")
    private BodyType bodyType;

    @JsonProperty("categories")
    private Set<String> categories;

    @JsonProperty("follower")
    private int follower;

    @JsonProperty("following")
    private int following;

    @JsonProperty("ootds")
    private Set<OotdMyPageResponse> ootds;

    public static final MemberMyPageResponse ofMember(final Member member) {
        final Set<String> categories = mapToStringCategoryNames(member);
        final MemberSpecs memberSpecs = member.memberSpecs();
        final Set<Follow> myFollowings = member.followers();
        final Set<Follow> myFollowers = member.followings();
        final Set<OotdMyPageResponse> ootds = mapToOotdMyPageResponseSet(member);
        return new MemberMyPageResponse(member.nickname(), member.gender(), member.memberRole(), member.picture(), member.description(),
                memberSpecs.height(), memberSpecs.weight(), memberSpecs.footSize(), memberSpecs.bodyType(),
                categories, myFollowers.size(), myFollowings.size(), ootds);
    }

    private static final Set<String> mapToStringCategoryNames(final Member member) {
        return member.memberCategories().stream()
                .map(MemberCategory::category)
                .map(Category::categoryName)
                .collect(Collectors.toSet());
    }

    private static final Set<OotdMyPageResponse> mapToOotdMyPageResponseSet(final Member member) {
        return member.ootds().stream()
                .map(OotdMyPageResponse::ofOotd)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public MemberMyPageResponse(final Nickname nickname, final Gender gender, final MemberRole role, final String picture, final String description,
                                final int height, final int weight, final int footSize, final BodyType bodyType,
                                final Set<String> categories, int follower, int following, final Set<OotdMyPageResponse> ootds) {
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
        this.picture = picture;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
        this.categories = categories;
        this.follower = follower;
        this.following = following;
        this.ootds = ootds;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final Gender gender() {
        return gender;
    }

    public final MemberRole role() {
        return role;
    }

    public final String picture() {
        return picture;
    }

    public final String description() {
        return description;
    }

    public final int height() {
        return height;
    }

    public final int weight() {
        return weight;
    }

    public final int footSize() {
        return footSize;
    }

    public final BodyType bodyType() {
        return bodyType;
    }

    public final Set<String> categories() {
        return categories;
    }

    public final int follower() {
        return follower;
    }

    public final int following() {
        return following;
    }

    public final Set<OotdMyPageResponse> ootds() {
        return ootds;
    }

}

