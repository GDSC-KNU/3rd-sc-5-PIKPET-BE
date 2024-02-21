package com.gdsc.pikpet.dto;

import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.Breed;
import com.gdsc.pikpet.entity.animal.Color;
import com.gdsc.pikpet.entity.animal.Species;
import java.util.List;


public record GeminiFilter(
        Species species,
        Breed breed,
        AnimalSize size,
        List<Color> color
) {
}