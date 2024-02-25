package com.gdsc.pikpet.controller;


import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.dto.UserLikeResponse;
import com.gdsc.pikpet.dto.response.AnimalDetailResponseDto;
import com.gdsc.pikpet.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("")
    public ResponseEntity<UserSecurityDto> userInfo(Authentication authentication) {
        UserSecurityDto userSecurityDto = (UserSecurityDto) authentication.getPrincipal();
        return ResponseEntity.ok(userSecurityDto);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUserInfo(Authentication authentication, @RequestBody UserSecurityDto userSecurityRequestDto) {
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userAccountService.updateUser(userSecurityRequestDto), authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        // TODO: 유저 변경이 제대로 되었는지 검사하는 로직이 필요할 수 있음.
        return ResponseEntity.ok().body("업데이트 완료");
    }

    @PostMapping("/likeAnimal")
    public ResponseEntity<UserLikeResponse> likeAnimal(
            Authentication authentication,
            @RequestParam Long animalId
    ) {
        UserLikeResponse userLikeResponse = userAccountService.addlikeAnimal((UserSecurityDto) authentication.getPrincipal(), animalId);
        return ResponseEntity.ok().body(userLikeResponse);
    }

    //TODO: fixMe: 좋아하는 "동물" 조회하도록 변경해야함
    @GetMapping("/likeAnimal")
    public ResponseEntity<?> getLikeAnimal(Authentication authentication) {
        List<AnimalDetailResponseDto> likeAnimals = userAccountService.getLikeAnimal((UserSecurityDto) authentication.getPrincipal());
        return ResponseEntity.ok().body(likeAnimals);
    }

    @GetMapping("/application/{animalId}")
    public ResponseEntity<?> getApplication(Authentication authentication, @PathVariable Long animalId  ) {
        return ResponseEntity.ok().body(userAccountService.getApplication((UserSecurityDto) authentication.getPrincipal(), animalId));
    }
    @GetMapping("/application")
    public ResponseEntity<?> getApplications(Authentication authentication) {
        return ResponseEntity.ok().body(userAccountService.getApplications((UserSecurityDto) authentication.getPrincipal()));
    }
    @PostMapping("/application")
    public ResponseEntity<?> postApplication(Authentication authentication, @RequestParam Long animalId, @RequestBody String content) {
        userAccountService.createApplication((UserSecurityDto) authentication.getPrincipal(), animalId, content );
        return ResponseEntity.ok().body("신청 완료");
    }
}
