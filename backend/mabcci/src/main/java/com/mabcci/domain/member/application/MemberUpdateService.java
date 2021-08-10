package com.mabcci.domain.member.application;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.BodyType;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.domain.PictureType;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class MemberUpdateService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PictureUtil pictureUtil;

    public MemberUpdateService(final MemberRepository memberRepository, final CategoryRepository categoryRepository,  final PictureUtil pictureUtil) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.pictureUtil = pictureUtil;
    }

    @Transactional
    public void update(final Nickname nickname, final Gender gender, final String description,
                         final int height, final int weight, final int footSize, final BodyType bodyType,
                         final Set<String> categories, final MultipartFile rawPicture) {

        final Member member = memberByNickName(nickname);
        final Picture picture = picture(rawPicture);
        updateCategory(categories, member);
        member.update(nickname, gender, description, height, weight, footSize, bodyType, picture.path());
    }

    private Member memberByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(MemberNotFoundException::new);
    }

    private Picture picture(final MultipartFile rawPicture) {
        final String pictureDirectory = pictureUtil.makeDirectory(PictureType.OOTD);
        return pictureUtil.savePicture(rawPicture, pictureDirectory);
    }

    private void updateCategory(final Set<String> categories, final Member member) {
        member.clearMemberCategory();
        for (String categoryName : categories) {
            final Category category = findCategoryByCategoryName(categoryName);
            final MemberCategory memberCategory = MemberCategory.fromMemberAndCategory(member, category);
            member.addMemberCategory(memberCategory);
        }
    }

    private Category findCategoryByCategoryName(final String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(IllegalArgumentException::new);
    }

}
