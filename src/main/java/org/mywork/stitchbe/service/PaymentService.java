/*
담당자: 박요한
시작 일자: 2024.09.24
설명 : 결제 service.
_____________________
2024.9.24 박요한 | 생성.
2024.9.25 김호영 | 결제 정보 확인 구현.
*/

package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.PaymentDTO;
import org.mywork.stitchbe.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 의존성 자동 주입
public class PaymentService {
    private final PaymentMapper paymentMapper;

    public void processPayment(PaymentDTO paymentDTO) {
        // 결제 정보 유효성 검사
        if (paymentDTO.getAmount() <= 0) {
            throw new IllegalArgumentException("결제 금액이 유효하지 않습니다.");
        }

        // 결제 정보 DB에 저장
        paymentMapper.insertPayment(paymentDTO);
    }
    // 모든 결제 정보 조회 (관리자용)
    public List<PaymentDTO> getAllPayments() {
        return paymentMapper.getAllPayments();
    }
    // 결제 상태 업데이트
    public void updatePaymentStatus(Long paymentId, String status) {
        paymentMapper.updatePaymentStatus(paymentId, status);
    }


}
