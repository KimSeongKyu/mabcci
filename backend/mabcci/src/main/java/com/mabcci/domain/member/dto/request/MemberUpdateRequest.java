package com.mabcci.domain.member.dto.request;

import com.mabcci.domain.member.domain.BodyType;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import java.util.Set;

public final class MemberUpdateRequest {

    @Valid private Nickname nickname;
    @Valid private Gender gender;
    private String description;
    private int height;
    private int weight;
    private int footSize;
    private BodyType bodyType;
    private Set<String> categories;

    private MemberUpdateRequest() {
    }

    public MemberUpdateRequest(final Nickname nickname, final Gender gender, final String description,
                               final int height, final int weight, final int footSize, final BodyType bodyType,
                               final Set<String> categories) {
        this.nickname = nickname;
        this.gender = gender;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
        this.categories = categories;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
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
}
