package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.dto.request.MemberDeleteRequest;
import com.mabcci.domain.member.dto.request.MemberJoinRequest;
import com.mabcci.domain.member.dto.request.MemberUpdateRequest;
import com.mabcci.domain.member.dto.response.FindMabcciResponse;
import com.mabcci.domain.member.dto.response.FindMemberByNickNameResponse;
import com.mabcci.domain.member.dto.response.MemberInfoResponse;
import com.mabcci.domain.member.dto.response.MemberListResponse;
import com.mabcci.domain.member.dto.response.FindMabcciResponses;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.mabcci.domain.member.domain.MemberRole.MABCCI;

@CrossOrigin(originPatterns = "http://localhost:*")
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
    public ResponseEntity<FindMemberByNickNameResponse> join(@Valid @RequestBody final MemberJoinRequest request) {
        final Member member = memberJoinService.join(request.member(), request.categories());
        final FindMemberByNickNameResponse response = FindMemberByNickNameResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/info/{nickname}")
    public ResponseEntity<MemberInfoResponse> findMemberInfoByNickName(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickname(nickname);
        final MemberInfoResponse response = MemberInfoResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<FindMemberByNickNameResponse> findMemberByNickname(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickname(nickname);
        final FindMemberByNickNameResponse response = FindMemberByNickNameResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberListResponse>> findAllMember() {
        final List<MemberListResponse> members = memberFindService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/api/members/mabcci")
    public ResponseEntity<FindMabcciResponses> findMabcci() {
        final List<Member> member = memberFindService.findByMemberRole(MABCCI);
        final List<FindMabcciResponse> responses = member.stream()
                .map(FindMabcciResponse::ofMember)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(new FindMabcciResponses(responses));
    }

    @PostMapping("/api/members/nickname")
    public ResponseEntity<?> update(@Valid @ModelAttribute MemberUpdateRequest request,
                                    @RequestParam MultipartFile picture) {
        final Member member = memberUpdateService.update(request.nickname(), request.gender(), request.description(),
                request.height(), request.weight(), request.footSize(), request.bodytype(),
                request.categories(), picture);
        // update 리스폰스 만들기
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequest request) {
        memberDeleteService.delete(request.nickname(), request.password());
        return ResponseEntity.ok().build();
    }

}
