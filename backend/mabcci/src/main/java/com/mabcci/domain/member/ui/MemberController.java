package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.dto.request.MemberDeleteRequest;
import com.mabcci.domain.member.dto.request.MemberJoinRequest;
import com.mabcci.domain.member.dto.request.MemberUpdateRequest;
import com.mabcci.domain.member.dto.response.MemberByMemberRoleResponse;
import com.mabcci.domain.member.dto.response.MemberByNickNameResponse;
import com.mabcci.domain.member.dto.response.MemberListResponse;
import com.mabcci.domain.member.ui.result.FindMabcciApiResult;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<MemberByNickNameResponse> join(@Valid @RequestBody final MemberJoinRequest request) {
        final Member member = memberJoinService.join(request.member(), request.getCategories());
        final MemberByNickNameResponse response = new MemberByNickNameResponse(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<MemberByNickNameResponse> findByNickname(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickname(nickname);
        final MemberByNickNameResponse response = new MemberByNickNameResponse(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberListResponse>> findAll() {
        final List<MemberListResponse> members = memberFindService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/api/members/mabcci")
    public ResponseEntity<FindMabcciApiResult<List<MemberByMemberRoleResponse>>> findByMabcci() {
        final List<Member> member = memberFindService.findByMemberRole(MemberRole.MABCCI);
        final List<MemberByMemberRoleResponse> responses = member.stream()
                .map(MemberByMemberRoleResponse::createMemberByMemberRoleResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(new FindMabcciApiResult<>(responses));
    }

    @PostMapping("/api/members/nickname")
    public ResponseEntity<?> update(@Valid @ModelAttribute MemberUpdateRequest request,
                                    @RequestParam MultipartFile picture) {
        final Member member = memberUpdateService.update(request.getNickname(), request.getGender(), request.getDescription(),
                request.getHeight(), request.getWeight(), request.getFootSize(), request.getBodyType(),
                request.getCategories(), picture);

        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequest request) {
        memberDeleteService.delete(request.getNickname(), request.getPassword());
        return ResponseEntity.ok().build();
    }

}
