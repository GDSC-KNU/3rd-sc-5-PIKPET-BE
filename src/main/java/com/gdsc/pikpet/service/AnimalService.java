package com.gdsc.pikpet.service;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.AnimalSearchDto;
import com.gdsc.pikpet.dto.AnimalFilterCriteria;
import com.gdsc.pikpet.dto.AnimalSimpleDto;
import com.gdsc.pikpet.dto.response.AnimalDetailResponseDto;
import com.gdsc.pikpet.entity.UserAccount;
import com.gdsc.pikpet.entity.UserLike;
import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.repository.AnimalRepository;
import com.gdsc.pikpet.repository.LikeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final UserAccountService userAccountService;
    private final AnimalRepository animalRepository;
    private final LikeRepository likeRepository;
    private final int PAGE_SIZE = 10;
    public AnimalDetailResponseDto getAnimalDetail(UserSecurityDto userSecurityDto, Long animalId) {
        UserAccount userAccount = userAccountService.getUserAccount(userSecurityDto);
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (!animal.isPresent()) throw new IllegalArgumentException("존재하지 않는 동물입니다");
        boolean isLiked = isLikedByUser(userAccount, animal.get());

        return animalRepository.findById(animalId)
                .map((Animal a) -> AnimalDetailResponseDto.from(a, isLiked))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동물을 찾을 수 없습니다 - animalId: " + animalId));
    }

    public AnimalSearchDto getAnimals(AnimalFilterCriteria animalFilterCriteria, UserSecurityDto userSecurityDto) {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);;
        if (animalFilterCriteria.page() != null) {
            pageable = PageRequest.of(animalFilterCriteria.page(), PAGE_SIZE);
        }

        Page<Animal> animals = animalRepository.findAnimalsWithFilter(
                animalFilterCriteria.species(),
                animalFilterCriteria.breeds(),
                animalFilterCriteria.minAge(),
                animalFilterCriteria.maxAge(),
                animalFilterCriteria.gender(),
                animalFilterCriteria.animalSize(),
                animalFilterCriteria.neutralized(),
                animalFilterCriteria.colors(),
                animalFilterCriteria.lon(),
                animalFilterCriteria.lat(),
                pageable
        );
        int numberOfElements = animals.getNumberOfElements();
        UserAccount userAccount = userAccountService.getUserAccount(userSecurityDto);
        List<AnimalSimpleDto> animalSimpleDtos = animals.getContent().stream()
                .map(animal -> {
                    boolean isLiked = isLikedByUser(userAccount, animal);
                    return AnimalSimpleDto.from(animal, isLiked);
                })
                .toList();

        AnimalSearchDto response = new AnimalSearchDto(animalSimpleDtos, animalFilterCriteria.page(), numberOfElements);
        return response;
    }

    private boolean isLikedByUser(UserAccount userAccount, Animal animal) {
        return likeRepository.findByUserAccountAndAnimal(userAccount, animal).isPresent();
    }
}