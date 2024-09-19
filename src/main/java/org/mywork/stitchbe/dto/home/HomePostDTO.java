/*
담당자: 박요한
시작 일자: 2024.09.17
설명 : 홈페이지 용 게시글 dto.
---------------------
2024.09.18 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto.home;
import lombok.Data;

@Data
public class HomePostDTO {
    private Long postId;   // 게시글 ID
    private String title;  // 게시글 제목
    private int views;     // 조회수
}
