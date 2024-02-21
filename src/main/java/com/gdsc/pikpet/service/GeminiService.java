package com.gdsc.pikpet.service;

import com.gdsc.pikpet.dto.request.GeminiRequest;
import com.gdsc.pikpet.dto.response.GeminiResponse;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${GEMINI_API_KEY}")
    private String GEMINI_API_KEY;

    private final RestTemplate restTemplate;
    public GeminiFilter imageToCategory(String image) {

        String url =  "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro-vision:generateContent?key="+GEMINI_API_KEY;

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 바디 설정
        GeminiRequest body = new GeminiRequest(
                List.of(
                        new GeminiRequest.Content(
                                List.of(
                                        new GeminiRequest.TextPart("Please provide the species, breed, size, and color of the animal in the picture. For species, it should be either dog or cat. For breed, choose one from the following: BEAGLE, BICHON_FRISE, BORDER_COLLIE, BOSTON_TERRIER, BULLDOG, CHIHUAHUA, COCKER_SPANIEL, DACHSHUND, GOLDEN_RETRIEVER, JAPANESE_SPITZ, JINDO, LABRADOR_RETRIEVER, MALTESE, PAPILLON, POMERANIAN, POODLE, POONGSAN, PUG, SAMOYED, SCHNAUZER, SHIBA_INU, SHITZU, SIBERIAN_HUSKY, WELSH_CORGI, YORKSHIRE_TERRIER, DALMATIAN, GERMAN_SHEPHERD, ROTTWEILER. Size should be one of the following: MINIATURE, SMALL, MEDIUM, LARGE, EXTRA_LARGE. An example is as follows: {\"species\":\"DOG\",\"breed\":\"DALMATIAN\",\"size\":\"LARGE\",\"color\": [\"black\",\"white\"]}"),
                                        new GeminiRequest.InlineDataPart(new GeminiRequest.InlineDataPart.InlineData("image/jpeg", image))
                                )

                        )
                )
        );
        HttpEntity<GeminiRequest> request = new HttpEntity<>(body, headers);
        GeminiResponse geminiResponse = restTemplate.postForObject(url, request, GeminiResponse.class);
        System.out.println(geminiResponse.candidates().get(0).content().parts().get(0).text());
          GeminiFilter geminiFilter = new Gson().fromJson(geminiResponse.candidates().get(0).content().parts().get(0).text(), GeminiFilter.class);
          return geminiFilter;
    }

    @Getter
    @Setter
    public static class GeminiFilter {
        String species;
        String breed;
        String size;
        List<String> color;

        // getters and setters
    }

}

