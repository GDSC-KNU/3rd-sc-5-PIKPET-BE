package com.gdsc.pikpet.dto;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.entity.animal.Breed;

public record AnimalSimpleDto(
        Long animalId,
        String imageUrl,
        Breed breed,
        int age,
        Gender gender,
        boolean isLiked
) {
    public static AnimalSimpleDto from(Animal animal, boolean isLiked){
        return new AnimalSimpleDto(
                animal.getId(),
                animal.getImageUrl(),
                animal.getBreed(),
                animal.getAge(),
                animal.getGender(),
                isLiked
        );
    }
}
