package com.mabcci.domain.category.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    public static final Category CATEGORY = new Category("categoryName");
    public static final Set<Category> CATEGORIES = new HashSet<>(Arrays.asList(CATEGORY));

    @DisplayName("Category 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Category category = new Category();

        assertAll(
                () -> assertThat(category).isNotNull(),
                () -> assertThat(category).isExactlyInstanceOf(Category.class)
        );
    }


    @DisplayName("Category 인스턴스 생성자를 이용한 생성 테스트")
    @Test
    void constructor_test() {
        final String categoryName = "categoryName";
        final Category category = new Category(categoryName);

        assertAll(
                () -> assertThat(category).isNotNull(),
                () -> assertThat(category).isExactlyInstanceOf(Category.class)
        );
    }

}