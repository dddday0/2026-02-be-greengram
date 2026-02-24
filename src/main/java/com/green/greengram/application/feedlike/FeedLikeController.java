package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeReq;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed/like")
public class FeedLikeController {
    private final FeedLikeService feedLikeService;

    @PostMapping
    public ResultResponse<?> toggleFeedLike(@AuthenticationPrincipal UserPrincipal userPrincipal
                                            , @RequestBody FeedLikeReq req){
        req.setSignedUserId( userPrincipal.getSignedUserId() );
        log.info("req:{}", req);
        boolean result = feedLikeService.toggleFeedLike( req );
        return new ResultResponse<>(result ? "좋아요 처리" : "좋아요 취소", result);
    }
}
