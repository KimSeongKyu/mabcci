package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public enum OotdFilter {

    ALL() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findOotds(pageable);
        }
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final String name, final Pageable pageable) {
            return Page.empty();
        }
    },
    FOLLOWING() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return ootdRepository.findOotdsOfFollowing(member, pageable);
        }
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final String name, final Pageable pageable) {
            return Page.empty();
        }
    },
    HASHTAG() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return Page.empty();
        }
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final String hashtagName, final Pageable pageable) {
            return ootdRepository.findOotdsByHashtagName(hashtagName, pageable);
        }
    },
    MEMBER() {
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable) {
            return Page.empty();
        }
        public Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final String nickname, final Pageable pageable) {
            return ootdRepository.findOotdsByNickname(Nickname.of(nickname), pageable);
        }
    }
    ;

    public abstract Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final Member member, final Pageable pageable);
    public abstract Page<Ootd> getFilteredOotds(final OotdRepository ootdRepository, final String name, final Pageable pageable);
}
