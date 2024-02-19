package com.gdsc.pikpet.dto;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.Color;
import com.gdsc.pikpet.entity.animal.Species;
import com.gdsc.pikpet.entity.animal.Breed;
import java.util.List;

public record AnimalFilterCriteria(

        List<Species> species,
        List<Breed> breeds,
        Integer minAge,
        Integer maxAge,
        List<Gender> gender,
        List<AnimalSize> animalSize,
        Boolean neutralized,
        Integer page,
        List<Color> colors
) {
}
