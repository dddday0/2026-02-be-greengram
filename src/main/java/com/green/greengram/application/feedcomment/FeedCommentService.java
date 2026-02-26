package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentMapper feedCommentMapper;

    public long postFeedComment(FeedCommentPostReq req){
        feedCommentMapper.save(req);
         return req.getId();
    }

    // moreComment 처리
    //1. size를 +1 해서 담는다. 실제 3개의 댓글을 가져오고 싶다면 size = 4
    //2. size에 3을 담았는데 알아서 +1이 되어 4가 담기도록.
    public List<FeedCommentGetRes> getFeedCommentList(FeedCommentGetReq req){
        List<FeedCommentGetRes> commentList = feedCommentMapper.findAll(req);
        return commentList;
    }

    public int deleteFeedComment(FeedCommentDeleteReq req){
        feedCommentMapper.delete(req);
        return 0;
    }
}
