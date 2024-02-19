package com.gdsc.pikpet.controller;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.AnimalSearchDto;
import com.gdsc.pikpet.dto.AnimalFilterCriteria;
import com.gdsc.pikpet.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalSearchController {
    private final AnimalService animalService;

    @RequestMapping("")
    public ResponseEntity<AnimalSearchDto> getAnimals(
            Authentication authentication,
            @ModelAttribute AnimalFilterCriteria animalFilterCriteria
    ) {
        AnimalSearchDto animals = animalService.getAnimals(animalFilterCriteria, (UserSecurityDto) authentication.getPrincipal());
        return ResponseEntity.ok(animals);
        //TODO: 예외처리
    }
}
