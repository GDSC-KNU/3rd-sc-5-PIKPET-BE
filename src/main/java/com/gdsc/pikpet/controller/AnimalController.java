package com.gdsc.pikpet.controller;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.response.AnimalDetailResponseDto;
import com.gdsc.pikpet.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDetailResponseDto> searchAnimalDetail(
            Authentication authentication,
            @PathVariable Long animalId
    ) {
        return ResponseEntity.ok(animalService.getAnimalDetail((UserSecurityDto) authentication.getPrincipal(), animalId));
    }

}
