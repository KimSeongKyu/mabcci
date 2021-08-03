package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.dto.*;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    private final MemberJoinService memberJoinService;
    private final MemberFindService memberFindService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;

    public MemberController(final MemberJoinService memberJoinService, final MemberFindService memberFindService,
                            final MemberUpdateService memberUpdateService, final MemberDeleteService memberDeleteService) {

        this.memberJoinService = memberJoinService;
        this.memberFindService = memberFindService;
        this.memberUpdateService = memberUpdateService;
        this.memberDeleteService = memberDeleteService;
    }

    @PostMapping(value = "/api/members")
    public ResponseEntity<MemberResponseDto> join(@Valid @RequestBody final MemberJoinRequest memberJoinRequest) {
        final MemberResponseDto joinedResponseDto = memberJoinService.join(memberJoinRequest.member(), memberJoinRequest.getCategories());
        return ResponseEntity.ok().body(joinedResponseDto);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<MemberResponseDto> findByNickname(@Valid @PathVariable final Nickname nickname) {
        final MemberResponseDto memberResponseDto = memberFindService.findByNickName(nickname);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberListResponseDto>> findAll() {
        final List<MemberListResponseDto> members = memberFindService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @PutMapping("/api/members/{nickname}")
    public ResponseEntity<?> update(@Valid @RequestBody final MemberUpdateRequestDto updateRequestDto) {
        final MemberResponseDto memberResponseDto = memberUpdateService.update(updateRequestDto);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequestDto memberDeleteRequestDto) {
        memberDeleteService.delete(memberDeleteRequestDto.getNickname(), memberDeleteRequestDto.getPassword());
        return ResponseEntity.ok().build();
    }

}
