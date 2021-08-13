package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public enum OotdFilter {

    ALL() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findOotds(pageable);
        }
    },
    FOLLOWING() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findOotdsOfFollowing(member, pageable);
        }
    };

    public abstract Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable);
}
