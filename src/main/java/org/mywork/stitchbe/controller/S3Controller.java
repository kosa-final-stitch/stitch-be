/*
담당자: 김호영
시작 일자: 2024.10.03
설명 : S3 이미지 파일 업로드 및 불러오기
---------------------
2024.10.03 김호영 | s3 연결 및 기능 구현.
*/

package org.mywork.stitchbe.controller;

import org.mywork.stitchbe.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    // S3에서 파일을 다운로드하는 API

}