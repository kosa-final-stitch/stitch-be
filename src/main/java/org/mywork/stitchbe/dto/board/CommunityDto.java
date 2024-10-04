package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class CommunityDto {
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private String category;
    private String tag;
    private Integer views;
    private Date regdate;
    private Date editdate;
    private String use_yn;
    private String nickname;
}

