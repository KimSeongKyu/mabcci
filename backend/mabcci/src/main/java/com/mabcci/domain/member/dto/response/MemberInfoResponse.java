package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.member.domain.*;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.global.common.Nickname;

import java.util.Set;
import java.util.stream.Collectors;

public final class MemberInfoResponse {

    private Nickname nickname;
    private Gender gender;
    private MemberRole role;
    private String picture;
    private String description;
    private int height;
    private int weight;
    private int footSize;
    private BodyType bodyType;
    private Set<String> categories;
    private int follower;
    private int following;

    public static final MemberInfoResponse createMemberInfoResponse(final Member member) {
        final Set<String> categories = mapToStringCategoryNames(member);
        final MemberSpecs memberSpecs = member.memberSpecs();
        final Set<Follow> followers = member.followers();
        final Set<Follow> following = member.followings();

        return new MemberInfoResponse(member.nickname(), member.gender(), member.memberRole(), member.picture(), member.description(),
                memberSpecs.height(), memberSpecs.weight(), memberSpecs.footSize(), memberSpecs.bodyType(),
                categories, followers.size(), following.size());
    }

    private static final Set<String> mapToStringCategoryNames(final Member member) {
        return member.memberCategories().stream()
                .map(MemberCategory::category)
                .map(Category::categoryName)
                .collect(Collectors.toSet());
    }

    MemberInfoResponse() {
    }

    public MemberInfoResponse(final Nickname nickname, final Gender gender, final MemberRole role, final String picture, final String description,
                                    final int height, final int weight, final int footSize, final BodyType bodyType,
                                    final Set<String> categories, int follower, int following) {
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
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public MemberRole getRole() {
        return role;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getFootSize() {
        return footSize;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public int getFollower() {
        return follower;
    }

    public int getFollowing() {
        return following;
    }

}

