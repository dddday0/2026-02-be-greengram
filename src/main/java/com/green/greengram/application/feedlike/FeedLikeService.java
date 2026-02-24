package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeService {
    private final FeedLikeMapper feedLikeMapper;
    private final DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher;

    //좋아요 처리 true
    //좋아요 처리 false
    // userId, feedId를 알고 있다.

    //0. delete를 한다.
    //1. 영향받은 행이 1이다.  return false;
    //2. 영향받은 행이 0이다.  insert 처리하고 return true;
    public boolean toggleFeedLike(FeedLikeReq req){
        int delAffectedRows = feedLikeMapper.delete(req);
        if(delAffectedRows == 1) {
            return false;
        }
        feedLikeMapper.save(req);
        return true;

        //switch (delAffectedRow) {
          //  case 0 :
        //  feedLikeMapper.save(req);
           // return true;
           // case 1 :
             //   return false;
            //default:
              //  return false;
        //}
    }
}
