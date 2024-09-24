/*
 담당자: 김호영
 시작 일자: 2024.09.24
 설명 : admin 공지사항 페이지 기능 구현 개발
 ---------------------
 2024.09.24 김호영 | 공지사항 작성 백엔드 연결.
 */

package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.board.NoticeDto;
import org.mywork.stitchbe.service.board.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 공지사항 작성
    @PostMapping
    public ResponseEntity<String> createNotice(@RequestBody NoticeDto noticeDto, Authentication authentication) {
        String email = authentication.getName(); // 로그인한 사용자의 이메일 가져오기
        Long memberId = noticeService.getMemberIdByEmail(email); // 이메일로 memberId를 가져오는 서비스 메서드 호출

        // 공지사항 작성할 때 memberId 설정
        noticeDto.setMemberId(memberId);
        noticeService.createNotice(noticeDto);

        return ResponseEntity.ok("공지사항 작성이 완료되었습니다.");
    }

    // 모든 공지사항 조회
    @GetMapping
    public ResponseEntity<List<NoticeDto>> getAllNotices() {
        List<NoticeDto> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    // 공지사항 수정
    @PutMapping("/{noticeId}")
    public ResponseEntity<String> updateNotice(@PathVariable Long noticeId, @RequestBody NoticeDto noticeDto) {
        noticeService.updateNotice(noticeId, noticeDto);
        return ResponseEntity.ok("공지사항이 수정되었습니다.");
    }

    // 공지사항 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return ResponseEntity.ok("공지사항이 삭제되었습니다.");
    }

    // 공지사항 상태 변경 (공개/비공개)
    @PutMapping("/{noticeId}/status")
    public ResponseEntity<String> updateNoticeStatus(@PathVariable Long noticeId, @RequestBody String status) {
        noticeService.updateNoticeStatus(noticeId, status);
        return ResponseEntity.ok("공지사항 상태가 변경되었습니다.");
    }
}