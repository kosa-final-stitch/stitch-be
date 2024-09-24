/*
 담당자: 김호영
 시작 일자: 2024.09.24
 설명 : admin 공지사항 페이지 기능 구현 개발
 ---------------------
 2024.09.24 김호영 | 공지사항 작성 백엔드 연결.
 */


package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.board.NoticeDto;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void insertNotice(NoticeDto noticeDto);
    List<NoticeDto> getAllNotices();
    void updateNotice(NoticeDto noticeDto);
    void deleteNotice(Long noticeId);
    void updateNoticeStatus(NoticeDto noticeDto);
}