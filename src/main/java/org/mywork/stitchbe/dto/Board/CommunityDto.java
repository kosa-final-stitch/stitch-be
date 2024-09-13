package org.mywork.stitchbe.dto.Board;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CommunityDto {
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private String category;
    private String tag;
    private int views;
    private LocalDate regdate;
    private LocalDate editdate;
    private String useYn;
}

