package co.kr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    @GetMapping("/favorite/search")
    public ResponseEntity<List<Map<String, Object>>> search(@RequestParam("query") String query) {
        try {
            List<Map<String, Object>> items = callNaverApi(query);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Map<String, Object>> callNaverApi(String query) {
        String url = "https://openapi.naver.com/v1/search/shop.json?query=" + query + "&display=5&start=1&sort=sim";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        // API 호출이 실패했을 때의 처리
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch data from Naver API");
        }

        List<Map<String, Object>> items = (List<Map<String, Object>>) response.getBody().get("items");
        return items;
    }
}
