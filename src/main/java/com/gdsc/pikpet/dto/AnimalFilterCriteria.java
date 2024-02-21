package com.gdsc.pikpet.dto;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.Color;
import com.gdsc.pikpet.entity.animal.Species;
import com.gdsc.pikpet.entity.animal.Breed;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record AnimalFilterCriteria(
        List<Species> species,
        List<Breed> breeds,
        Integer minAge,
        Integer maxAge,
        List<Gender> gender,
        List<AnimalSize> animalSize,
        Boolean neutralized,
        Double lon,
        Double lat,
        Integer page,
        List<Color> colors
) {
    public static AnimalFilterCriteria from(GeminiFilter geminiFilter) {
        AnimalFilterCriteria animalFilterCriteria = new AnimalFilterCriteria(
                List.of(geminiFilter.species()),
                List.of(geminiFilter.breed()),
                null,
                null,
                null,
                List.of(geminiFilter.size()),
                null,
                null,
                null,
                0,
                null //TODO: color 코드로 변경
        );
        return animalFilterCriteria;
    }
}
