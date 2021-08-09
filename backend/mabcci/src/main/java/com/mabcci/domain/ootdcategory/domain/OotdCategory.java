package com.mabcci.domain.ootdcategory.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.ootd.domain.Ootd;

import javax.persistence.*;

@Entity
public class OotdCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ootd_id")
    private Ootd ootd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static OotdCategory createOotdCategory(final Ootd ootd, final Category category) {
        return new OotdCategory(ootd, category);
    }

    protected OotdCategory() {}

    protected OotdCategory(final Ootd ootd, final Category category) {
        this.ootd = ootd;
        this.category = category;
    }

    public void changeOotd(final Ootd ootd) {
        this.ootd = ootd;
    }

}
