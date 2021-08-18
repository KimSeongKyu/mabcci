package com.mabcci.domain.ootdLike.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
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
    private final OotdRepository ootdRepository;

    public OotdLikeSaveAndUpdateService(final OotdLikeRepository ootdLikeRepository, final MemberRepository memberRepository,
                                        final OotdRepository ootdRepository) {
        this.ootdLikeRepository = ootdLikeRepository;
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
    }

    @Transactional
    public void saveOrUpdateOotdLike(final Long ootdId, final Nickname nickname) {
        final Optional<OotdLike> ootdLike = saveOrGetOotdLikeByOotdIdAndNickname(ootdId, nickname);
        if(ootdLike.isPresent()) {
            ootdLike.get().updateStatus();
        }
    }

    private Optional<OotdLike> saveOrGetOotdLikeByOotdIdAndNickname(final Long ootdId, final Nickname nickname) {
        return ootdLikeRepository.findByOotdAndNickname(ootdId, nickname)
                .or(() -> {
                    saveOotdLike(getOotdById(ootdId), getMemberByNickname(nickname));
                    return Optional.empty();
                });
    }

    private Ootd getOotdById(final Long id) {
        return ootdRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
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
