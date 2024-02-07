package com.gdsc.pikpet.service;

import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.entity.Animal;
import com.gdsc.pikpet.entity.UserAccount;
import com.gdsc.pikpet.entity.UserLike;
import com.gdsc.pikpet.repository.AnimalRepository;
import com.gdsc.pikpet.repository.LikeRepository;
import com.gdsc.pikpet.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final AnimalRepository animalRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public UserSecurityDto updateUser(UserSecurityDto userSecurityDto) {
        UserAccount userAccount = userAccountRepository.findByEmail(userSecurityDto.email()).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다 - email: " + userSecurityDto.email()));
        userAccount.update(UserSecurityDto.toEntity(userSecurityDto));
        userAccountRepository.save(userAccount);
        return UserSecurityDto.from(userAccount);
    }

    @Transactional
    public void addlikeAnimal(UserSecurityDto userSecurityDto, Long animalId) {
        UserAccount userAccount = userAccountRepository.findByEmail(userSecurityDto.email()).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다 - email: " + userSecurityDto.email()));
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("동물을 찾을 수 없습니다 - id: " + animalId));
        likeRepository.save(UserLike.of(userAccount, animal));
    }
}
