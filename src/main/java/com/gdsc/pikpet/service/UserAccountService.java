package com.gdsc.pikpet.service;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.UserLikeResponse;
import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.entity.Application;
import com.gdsc.pikpet.entity.UserAccount;
import com.gdsc.pikpet.entity.UserLike;
import com.gdsc.pikpet.repository.AnimalRepository;
import com.gdsc.pikpet.repository.ApplicationRepository;
import com.gdsc.pikpet.repository.LikeRepository;
import com.gdsc.pikpet.repository.UserAccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final AnimalRepository animalRepository;
    private final LikeRepository likeRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public UserSecurityDto updateUser(UserSecurityDto userSecurityDto) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        userAccount.update(UserSecurityDto.toEntity(userSecurityDto));
        userAccountRepository.save(userAccount);
        return UserSecurityDto.from(userAccount);
    }

    @Transactional
    public UserLikeResponse addlikeAnimal(UserSecurityDto userSecurityDto, Long animalId) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("동물을 찾을 수 없습니다 - id: " + animalId));
        Optional<UserLike> existedUserLike = likeRepository.findByUserAccountAndAnimal(userAccount, animal);
        if (existedUserLike.isPresent()) {
            likeRepository.delete(existedUserLike.get());
            return new UserLikeResponse(false,"관심동물 삭제 완료");
        }
        likeRepository.save(UserLike.of(userAccount, animal));
        return new UserLikeResponse(true,"관심동물 설정 완료");
    }

    @Transactional(readOnly = true)
    public List<UserLike> getLikeAnimal(UserSecurityDto userSecurityDto) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        return likeRepository.findAllByUserAccount(userAccount);
    }

    @Transactional
    public void createApplication(UserSecurityDto userSecurityDto, Long animalId, String content) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("동물을 찾을 수 없습니다 - id: " + animalId));
        applicationRepository.save(Application.of(userAccount, animal, content));
    }

    @Transactional(readOnly = true)
    public Application getApplication(UserSecurityDto userSecurityDto, Long animalId) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("동물을 찾을 수 없습니다 - id: " + animalId));
        return applicationRepository.findByUserAccountAndAnimal(userAccount, animal).orElseThrow(() -> new IllegalArgumentException("신청을 찾을 수 없습니다 - animalId: " + animalId));
    }

    @Transactional(readOnly = true)
    public List<Application> getApplications(UserSecurityDto userSecurityDto) {
        UserAccount userAccount = getUserAccount(userSecurityDto);
        return applicationRepository.findAllByUserAccount(userAccount);
    }

    public UserAccount getUserAccount(UserSecurityDto userSecurityDto) {
        return userAccountRepository.findByEmail(userSecurityDto.email()).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다 - email: " + userSecurityDto.email()));
    }
}
