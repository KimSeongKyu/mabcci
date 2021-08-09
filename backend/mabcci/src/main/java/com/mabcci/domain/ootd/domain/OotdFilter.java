package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public enum OotdFilter {

    ALL() {
        public Page<Ootd> findOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findAll(pageable);
        }
    },
    FOLLOWING() {
        public Page<Ootd> findOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findAllOfFollowing(member, pageable);
        }
    };

    public abstract Page<Ootd> findOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable);
}
