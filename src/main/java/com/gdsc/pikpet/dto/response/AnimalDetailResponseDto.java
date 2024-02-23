package com.gdsc.pikpet.dto.response;

import com.gdsc.pikpet.entity.*;

import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.AnimalColor;
import com.gdsc.pikpet.entity.animal.Breed;
import com.gdsc.pikpet.entity.animal.Color;
import com.gdsc.pikpet.entity.animal.Species;
import java.time.LocalDateTime;
import java.util.List;

public record AnimalDetailResponseDto(
        Long id,
        String imageUrl,
        Species species,
        Gender gender,
        AnimalSize size,
        String disease,
        // Shelter를 노출시키는 것이 좋을지에 대한 고민이 필요함. ShelterId만 노출시키는 것이 좋을 수 있음
        Shelter shelter,
        boolean isNeutralized,
        boolean checkUp,
        LocalDateTime captureDate,
        LocalDateTime euthanasiaDate,
        List<Color> colors,
        Integer age,
        Breed breed,
        Boolean isLiked
) {
    public static AnimalDetailResponseDto from(Animal animal, Boolean isLiked) {
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
                animal.getAnimalColors().stream()
                        .map(AnimalColor::getColor)
                .toList(),
                animal.getAge(),
                animal.getBreed(),
                isLiked
        );
    }
}
