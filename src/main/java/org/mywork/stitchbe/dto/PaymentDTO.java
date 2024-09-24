/*
담당자: 박요한
시작 일자: 2024.09.24
설명 : 결제 용 dto.
---------------------
2024.09.24 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PaymentDTO {
    private Long paymentId;     // 결제 ID (DB에서 자동 생성)
    private Long memberId;      // 사용자 ID (로그인 사용자)
    private double amount;      // 결제 금액
    private String method;      // 결제 방식 (카드 등)
    private String category;    // 결제 카테고리 (후원 등)
    private String status;      // 결제 상태 (예: completed, refunded)
    private Date payDate;       // 결제 일시
    private Date editDate;      // 상태 변경 일시 (null 허용)
}
