/*
 담당자: 김호영
 시작 일자: 2024.09.24
 설명 : admin 공지사항 페이지 기능 구현 개발
 ---------------------
 2024.09.24 김호영 | 공지사항 작성 백엔드 연결.
 */


package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDto {
    private Long noticeId;
    private Long memberId;
    private String title;
    private String content;
    private int views;
    private Date regdate;
    private Date editdate;
    private String status;
    private String adminName;
    private Boolean isPinned;

}

