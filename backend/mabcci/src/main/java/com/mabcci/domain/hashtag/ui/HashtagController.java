package com.mabcci.domain.hashtag.ui;

import com.mabcci.domain.hashtag.application.HashtagService;
import com.mabcci.domain.hashtag.dto.HashtagFindByNicknameContainsResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class HashtagController {

    private final HashtagService hashtagService;

    public HashtagController(final HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("/api/hashtags/search")
    public HashtagFindByNicknameContainsResponse findHashtagNamesContains(final String hashtag) {
        return hashtagService.findByNameContains(hashtag);
    }
}
