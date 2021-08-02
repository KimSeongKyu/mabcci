package com.mabcci.domain.category.domain;

import com.mabcci.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Category extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    protected Category() {
    }

    public Category(final String categoryName) {
        this.categoryName = categoryName;
    }

}
