package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FeedCommentGetRes {
    private boolean moreComment;
    private List<FeedCommentDto> commentList = new ArrayList<>();


}
