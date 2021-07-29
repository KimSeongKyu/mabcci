package com.mabcci.domain.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberSpecs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int height;
    private int weight;
    private int footSize;
    private BodyForm form;

    protected MemberSpecs() {
    }

    private MemberSpecs(MemberSpecsBuilder memberSpecsBuilder) {
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
