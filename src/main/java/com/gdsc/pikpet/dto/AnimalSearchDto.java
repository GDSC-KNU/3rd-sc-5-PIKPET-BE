package com.gdsc.pikpet.dto;

import java.util.List;

public record AnimalSearchDto(
    List<AnimalSimpleDto> animals,
    Integer page
) {
}
