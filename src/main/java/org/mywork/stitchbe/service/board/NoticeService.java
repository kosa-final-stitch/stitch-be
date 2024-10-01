/*
 담당자: 김호영
 시작 일자: 2024.09.24
 설명 : admin 공지사항 페이지 기능 구현 개발
 ---------------------
 2024.09.24 김호영 | 공지사항 작성 백엔드 연결.
 2024.10.01 김호영 | 공지사항 공개, 비공개 구현.
 */


package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.NoticeDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.mywork.stitchbe.mapper.board.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final MemberMapper memberMapper; // 이메일로 멤버 조회를 위한 매퍼

    @Autowired
    public NoticeService(NoticeMapper noticeMapper, MemberMapper memberMapper) {
        this.noticeMapper = noticeMapper;
        this.memberMapper = memberMapper;
    }

    public Long getMemberIdByEmail(String email) {
        return memberMapper.findMemberIdByEmail(email);
    }

    // 공지사항 작성
    public void createNotice(NoticeDto noticeDto) {
        noticeDto.setViews(0);
        if (noticeDto.getStatus() == null) {
            noticeDto.setStatus("public");
        }
        noticeMapper.insertNotice(noticeDto);
    }

    // 모든 공지사항 조회
    public List<NoticeDto> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    // 공개 공지사항 조회
    public List<NoticeDto> getPublicNotices() {
        return noticeMapper.getPublicNotices(); // MyBatis 쿼리 호출
    }


    // 공지사항 수정
    public void updateNotice(Long noticeId, NoticeDto noticeDto) {
        noticeDto.setNoticeId(noticeId);
        noticeMapper.updateNotice(noticeDto);
    }

    // 공지사항 삭제
    public void deleteNotice(Long noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }

    // 공지사항 상태 변경
    public void updateNoticeStatus(Long noticeId, String status) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNoticeId(noticeId);
        noticeDto.setStatus(status);
        noticeMapper.updateNoticeStatus(noticeDto);
    }
}