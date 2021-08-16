package com.mabcci.domain.member.domain;

import javax.persistence.*;

@Entity
public class MemberSpecs {

    private static final int INIT_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_specs_id")
    private Long id;

    @Column(name = "member_specs_height")
    private int height;

    @Column(name = "member_specs_weight")
    private int weight;

    @Column(name = "member_specs_foot_size")
    private int footSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_specs_type")
    private BodyType bodyType;

    public static MemberSpecs noContent() {
        return new MemberSpecs(INIT_VALUE, INIT_VALUE, INIT_VALUE, BodyType.NONE);
    }

    protected MemberSpecs() {
    }

    protected MemberSpecs(final MemberSpecsBuilder memberSpecsBuilder) {
        this.height = memberSpecsBuilder.height;
        this.weight = memberSpecsBuilder.weight;
        this.footSize = memberSpecsBuilder.footSize;
        this.bodyType = memberSpecsBuilder.bodyType;
    }

    private MemberSpecs(final int height, final int weight, final int footSize, final BodyType bodyType) {
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
    }

    public static MemberSpecsBuilder Builder() {
        return new MemberSpecsBuilder();
    }

    public static class MemberSpecsBuilder {
        private int height;
        private int weight;
        private int footSize;
        private BodyType bodyType;

        private MemberSpecsBuilder() {
        }

        public MemberSpecsBuilder height(final int height) {
            this.height = height;
            return this;
        }

        public MemberSpecsBuilder weight(final int weight) {
            this.weight = weight;
            return this;
        }

        public MemberSpecsBuilder footSize(final int footSize) {
            this.footSize = footSize;
            return this;
        }

        public MemberSpecsBuilder form(final BodyType bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        public MemberSpecs build() {
            return new MemberSpecs(this);
        }
    }


    public int height() {
        return height;
    }

    public int weight() {
        return weight;
    }

    public int footSize() {
        return footSize;
    }

    public BodyType bodyType() {
        return bodyType;
    }

    public MemberSpecs update(int height, int weight, int footSize, final BodyType bodyType) {
        this.height = height;
        this.weight = weight;
        this.footSize = footSize;
        this.bodyType = bodyType;
        return this;
    }

}
