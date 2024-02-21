package com.gdsc.pikpet.controller;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.AnimalSearchDto;
import com.gdsc.pikpet.dto.AnimalFilterCriteria;
import com.gdsc.pikpet.dto.GeminiFilter;
import com.gdsc.pikpet.service.AnimalService;
import com.gdsc.pikpet.service.GeminiService;
import java.io.IOException;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalSearchController {
    private final AnimalService animalService;
    private final GeminiService geminiService;

    @RequestMapping("")
    public ResponseEntity<AnimalSearchDto> getAnimals(
            Authentication authentication,
            @ModelAttribute AnimalFilterCriteria animalFilterCriteria
    ) {
        AnimalSearchDto animals = animalService.getAnimals(animalFilterCriteria, (UserSecurityDto) authentication.getPrincipal());
        return ResponseEntity.ok(animals);
        //TODO: 예외처리
    }

    @PostMapping("/image")
    public ResponseEntity<AnimalSearchDto> getAnimalsByImage(
            Authentication authentication,
            @RequestParam MultipartFile file
    ) throws IOException {
        //TODO: 예외처리
        byte[] fileContent = file.getBytes();
        GeminiFilter geminiFilter = geminiService
                .imageToCategory(Base64.getEncoder().encodeToString(fileContent));

        //GeminiFilter -> AnimalFilterCriteria로 변환
        AnimalFilterCriteria animalFilterCriteria = AnimalFilterCriteria.from(geminiFilter);
        AnimalSearchDto animals = animalService.getAnimals(animalFilterCriteria, (UserSecurityDto) authentication.getPrincipal());
        log.info("geminifilter: {}", geminiFilter);
        return ResponseEntity.ok(animals);

    }

}
