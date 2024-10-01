/*
담당자: 박요한
시작 일자: 2024.09.24
설명 : 결제 mapper.
_____________________
2024.9.24 박요한 | 생성.
2024.9.25 김호영 | 결제 정보 확인 구현.
*/

package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.PaymentDTO;

import java.util.List;

@Mapper
public interface PaymentMapper {
    // 결제 완료 정보 등록 메서드
    void insertPayment(PaymentDTO payment);

    // 모든 결제 정보 조회 메서드
    List<PaymentDTO> getAllPayments();

    // 결제 상태 업데이트 메서드
    void updatePaymentStatus(Long paymentId, String status);
}
