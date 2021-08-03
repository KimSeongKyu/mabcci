package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberSpecs;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public MemberJoinService(final MemberRepository memberRepository, final CategoryRepository categoryRepository) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Member join(final Member member, final Set<String> categories) {
        final MemberSpecs memberSpecs = MemberSpecs.noContent();
        member.updateMemberSpecs(memberSpecs);
        for (String categoryName : categories) {
            final Category category = getCategoryByCategoryName(categoryName);
            final MemberCategory memberCategory = MemberCategory.createMemberCategory(member, category);
            member.addMemberCategory(memberCategory);
        }
        return memberRepository.save(member);
    }

    private Category getCategoryByCategoryName(final String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(IllegalArgumentException::new);
    }

}
