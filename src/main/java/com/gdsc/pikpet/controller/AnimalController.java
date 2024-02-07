package com.gdsc.pikpet.controller;

import com.gdsc.pikpet.dto.response.AnimalDetailResponseDto;
import com.gdsc.pikpet.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDetailResponseDto> searchAnimalDetail(@PathVariable Long animalId) {
        return ResponseEntity.ok(animalService.getAnimalDetail(animalId));
    }
//    @GetMapping("/location")
//    // imageUrl을 통해 가져온 이미지를 통해 동물을 검색하고, 해당 동물이 유저 반경에 있다면 Page에 담아서 return
//    public ResponseEntity<?> searchAnimal(
//
//    )
}
