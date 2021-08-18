package com.mabcci.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.BodyType;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.global.common.Nickname;

import java.util.Set;

public final class MemberUpdateRequest {

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("nickname")
    private Nickname nickname;

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

    MemberUpdateRequest() {
    }

    public MemberUpdateRequest(final Gender gender, final String description, final Nickname nickname,
                               final int height, final int weight, final int footSize, final BodyType bodyType,
                               final Set<String> categories) {
        this.gender = gender;
        this.description = description;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
        this.categories = categories;
    }

    public final Gender gender() {
        return gender;
    }

    public final String description() {
        return description;
    }

    public Nickname nickname() {
        return nickname;
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

    public final BodyType bodytype() {
        return bodyType;
    }

    public final Set<String> categories() {
        return categories;
    }

    @Override
    public String toString() {
        return "MemberUpdateRequest{" +
                "gender=" + gender +
                ", nickname=" + nickname +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", footSize=" + footSize +
                ", bodyType=" + bodyType +
                ", categories=" + categories +
                '}';
    }

}
