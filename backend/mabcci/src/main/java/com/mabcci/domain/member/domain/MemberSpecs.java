package com.mabcci.domain.member.domain;

import javax.persistence.*;

@Entity
public class MemberSpecs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_specs_id")
    private Long id;

    @Column(name = "member_specs_height")
    private int height;

    @Column(name = "member_specs_weight")
    private int weight;

    @Column(name = "member_specs_foot_size")
    private int footSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_specs_form")
    private BodyForm form;

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getFootSize() {
        return footSize;
    }

    public BodyForm getForm() {
        return form;
    }

    protected MemberSpecs() {
    }

    private MemberSpecs(final MemberSpecsBuilder memberSpecsBuilder) {
        this.height = memberSpecsBuilder.height;
        this.weight = memberSpecsBuilder.weight;
        this.footSize = memberSpecsBuilder.footSize;
        this.form = memberSpecsBuilder.form;
    }

    public static MemberSpecsBuilder Build() {
        return new MemberSpecsBuilder();
    }

    public static class MemberSpecsBuilder {
        private int height;
        private int weight;
        private int footSize;
        private BodyForm form;

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

        public MemberSpecsBuilder form(final BodyForm form) {
            this.form = form;
            return this;
        }

        public MemberSpecs build() {
            return new MemberSpecs(this);
        }
    }
}
