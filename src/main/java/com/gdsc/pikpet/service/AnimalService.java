package com.gdsc.pikpet.service;

import com.gdsc.pikpet.dto.response.AnimalDetailResponseDto;
import com.gdsc.pikpet.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public AnimalDetailResponseDto getAnimalDetail(Long animalId) {
        return animalRepository.findById(animalId)
                .map(AnimalDetailResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동물을 찾을 수 없습니다 - animalId: " + animalId));
    }
}
