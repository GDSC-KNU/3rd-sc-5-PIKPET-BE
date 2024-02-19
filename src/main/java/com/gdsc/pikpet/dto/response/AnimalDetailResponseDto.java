package com.gdsc.pikpet.dto.response;

import com.gdsc.pikpet.entity.*;

import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.Species;
import java.time.LocalDateTime;
import java.util.List;

public record AnimalDetailResponseDto(
        Long id,
        String imageUrl,
        Species species,
        Gender gender,
        AnimalSize size,
        String deasease,
        // Shelter를 노출시키는 것이 좋을지에 대한 고민이 필요함. ShelterId만 노출시키는 것이 좋을 수 있음
        Shelter shelter,
        boolean isNeutralized,
        boolean checkUp,
        LocalDateTime captureDate,
        LocalDateTime enthanasiaDate,
        List<String> color
) {
    public static AnimalDetailResponseDto from(Animal animal) {
        return new AnimalDetailResponseDto(
                animal.getId(),
                animal.getImageUrl(),
                animal.getSpecies(),
                animal.getGender(),
                animal.getSize(),
                animal.getDisease(),
                animal.getShelter(),
                animal.isNeutralized(),
                animal.isCheckUp(),
                animal.getCaptureDate(),
                animal.getEnthanasiaDate(),
                animal.getColor()
        );
    }
}
