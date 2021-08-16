package com.mabcci.domain.ootdLike.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdLike.domain.OotdLikeRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OotdLikeSaveAndUpdateService {

    private final OotdLikeRepository ootdLikeRepository;
    private final MemberRepository memberRepository;

    public OotdLikeSaveAndUpdateService(final OotdLikeRepository ootdLikeRepository, final MemberRepository memberRepository) {
        this.ootdLikeRepository = ootdLikeRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void saveOrUpdateOotdLike(final Ootd ootd, final Nickname nickname) {
        final Optional<OotdLike> ootdLike = saveOrGetOotdLikeByOotdAndNickname(ootd, nickname);
        if(ootdLike.isPresent()) {
            ootdLike.get().updateStatus();
        }
    }

    private Optional<OotdLike> saveOrGetOotdLikeByOotdAndNickname(final Ootd ootd, final Nickname nickname) {
        return ootdLikeRepository.findByOotdAndNickname(ootd, nickname)
                .or(() -> {
                    final Member member = getMemberByNickname(nickname);
                    saveOotdLike(ootd, member);
                    return Optional.empty();
                });
    }

    private Member getMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void saveOotdLike(final Ootd ootd, final Member member) {
        ootdLikeRepository.save(buildOotdLike(ootd, member));
    }

    private OotdLike buildOotdLike(final Ootd ootd, final Member member) {
        return OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build();
    }
}
