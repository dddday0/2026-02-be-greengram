package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedGetReq;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feed.model.FeedPostRes;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.configuration.util.ImgUploadManager;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final ImgUploadManager imgUploadManager;

    public FeedPostRes postFeed(FeedPostReq req, List<MultipartFile> pics) {
        feedMapper.save(req);

        //save 이후 방금 insert한 feed 테이블의 id값이 필요해요.
        long feedId = req.getFeedId();
        log.info("feedId:{}", feedId);

        //saveFeedPics 메소드 호출
        List<String> picSavedNames = imgUploadManager.saveFeedPics(feedId, pics);
        feedMapper.savePics(feedId, picSavedNames);

        return new FeedPostRes(feedId, picSavedNames);
    }

    public List<FeedGetRes> getFeedList(FeedGetReq req){
        List<FeedGetRes> list = feedMapper.findAll(req);
        //작업!! 피드 당 사진 정보를 가져오는 작업을 해야 한다.
        for(FeedGetRes res : list) {
            List<String> pics = feedMapper.findPicsById(res.getId());
                res.setPics(pics);

                //TODO: 댓글 가져오는 select
                res.setComments( new FeedCommentGetRes() );
        }
        return list;
    }


}
