package org.mywork.stitchbe.integration;

import org.mywork.stitchbe.dto.ApiCourseData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Work24Api {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://www.work24.go.kr/cm/openApi/call/hr/callOpenApiSvcInfo310L01.do";

    // API 데이터를 페이지별로 가져오기
    public List<ApiCourseData> fetchApiData(Map<String, String> params) {
        List<ApiCourseData> allData = new ArrayList<>();

        for (int pageNum = 1; pageNum <= 1000; pageNum++) {
            params.put("pageNum", String.valueOf(pageNum));  // 페이지 번호 설정

            String apiUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("authKey", params.get("authKey"))
                    .queryParam("returnType", params.get("returnType"))
                    .queryParam("outType", params.get("outType"))
                    .queryParam("pageNum", params.get("pageNum"))
                    .queryParam("pageSize", params.get("pageSize"))
                    .queryParam("srchTraStDt", params.get("srchTraStDt"))
                    .queryParam("srchTraEndDt", params.get("srchTraEndDt"))
                    .queryParam("sort", params.get("sort"))
                    .queryParam("sortCol", params.get("sortCol"))
                    .queryParam("srchNcs1", params.get("srchNcs1"))
                    .toUriString();

//            try {
//                ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);
//
//                if (response != null && response.getSrchList() != null) {
//                    allData.addAll(response.getSrchList());
//                } else {
//                    break;  // 더 이상 데이터가 없을 경우 반복문 종료
//                }
//
//            } catch (Exception e) {
//                break;  // 에러 발생 시 중단
//            }
        }

        return allData;
    }
}
