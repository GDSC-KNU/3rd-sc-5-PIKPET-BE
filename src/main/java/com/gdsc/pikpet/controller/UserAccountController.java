package com.gdsc.pikpet.controller;


import com.gdsc.pikpet.config.security.UserSecurityDto;
import com.gdsc.pikpet.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> likeAnimal(Authentication authentication, @RequestParam Long animalId) {
        userAccountService.addlikeAnimal((UserSecurityDto) authentication.getPrincipal(), animalId);
        return ResponseEntity.ok().body("관심동물 설정 완료");
    }
}
