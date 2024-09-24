/*
담당자: 박요한
시작 일자: 2024.09.24
설명 : 결제 mapper.
_____________________
2024.9.24 박요한 | 생성.
*/

package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.PaymentDTO;

@Mapper
public interface PaymentMapper {
    // 결제 완료 정보 등록 메서드
    void insertPayment(PaymentDTO payment);
}
