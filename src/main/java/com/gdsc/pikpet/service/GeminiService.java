package com.gdsc.pikpet.service;

import com.gdsc.pikpet.dto.request.GeminiRequest;
import com.gdsc.pikpet.dto.response.GeminiResponse;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GeminiService {
    private final RestTemplate restTemplate;
    public Dog imageTOCategory(String image) {
        String url =  "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro-vision:generateContent?key=AIzaSyDOGSlq7Gce64r7ysLbomo5inuB7lLLAYg";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 바디 설정
        GeminiRequest body = new GeminiRequest(
                List.of(
                        new GeminiRequest.Content(
                                List.of(
                                        new GeminiRequest.TextPart("사진의 동물의 종이 무엇인지, 품종은 무엇인지, 크기는 어떤지, 어떤 색상인지 저장할 수 있는 text 파일로 줘.예시는 다음과 같아 {\n" +
                                                "\t\"종\": \"강아지\",\n" +
                                                "\t\"품종\": \"보더콜리\",\n" +
                                                "\t\"크기\": \"대형견\",\n" +
                                                "\t\"색상\": [\"검정\",\"흰색\"]\n" +
                                                "}\""),
                                        new GeminiRequest.InlineDataPart(new GeminiRequest.InlineDataPart.InlineData("image/jpeg", image))
                                )

                        )
                )
        );
        HttpEntity<GeminiRequest> request = new HttpEntity<>(body, headers);
        GeminiResponse geminiResponse = restTemplate.postForObject(url, request, GeminiResponse.class);
        System.out.println(geminiResponse.candidates().get(0).content().parts().get(0).text());
          Dog dog = new Gson().fromJson(geminiResponse.candidates().get(0).content().parts().get(0).text(), Dog.class);
          return dog;
    }

    @Getter
    @Setter
    public static class Dog {
        String 종;
        String 품종;
        String 크기;
        List<String> 색상;

        // getters and setters
    }

}