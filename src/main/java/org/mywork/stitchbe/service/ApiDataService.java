package org.mywork.stitchbe.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
public class ApiDataService {
//
//    @Autowired
//    private ApiClient apiClient;
//
//    @Autowired
//    private AcademyMapper academyMapper;
//
//    @Autowired
//    private CourseMapper courseMapper;
//
//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
//
//    @Transactional
//    public void fetchAndProcessApiData() {
//        // 현재 월의 첫째 날
//        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
//        // 3개월 후의 마지막 날
//        LocalDate endDate = startDate.plusMonths(3).withDayOfMonth(startDate.plusMonths(3).lengthOfMonth());
//
//        // API 요청 파라미터 설정
//        Map<String, String> params = new HashMap<>();
//        params.put("authKey", "cc4241f5-7522-4e3d-8195-5c703c6b87ef");
//        params.put("returnType", "JSON");
//        params.put("outType", "1");
//        params.put("pageSize", "100");
//        params.put("srchTraStDt", startDate.format(DATE_FORMATTER));  // 훈련 시작일 From
//        params.put("srchTraEndDt", endDate.format(DATE_FORMATTER));   // 훈련 시작일 To
//        params.put("sort", "ASC");
//        params.put("sortCol", "TRNG_BGDE");
//        params.put("srchNcs1", "20");
//
//        // API 데이터 가져오기
//        List<ApiCourseData> apiDataList = apiClient.fetchApiData(params);
//
//        // 데이터 처리 (학원과 교육 과정 전처리 및 DB 삽입)
//        processApiData(apiDataList);
//    }
//
//    // 학원과 교육 과정 전처리 및 DB 삽입
//    private void processApiData(List<ApiCourseData> apiDataList) {
//        // 학원 및 교육 과정 처리 로직...
//    }
}
