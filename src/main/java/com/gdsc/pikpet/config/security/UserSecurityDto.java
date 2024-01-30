package com.gdsc.pikpet.config.security;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.UserAccount;
import com.gdsc.pikpet.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record UserSecurityDto(
        String email,
        String password,
        String phoneNumber,
        Gender gender,
        int age,
        String address,
        String job,
        UserRole userRole
) implements UserDetails {


    public static UserAccount toEntity(UserSecurityDto userSecurityDto){
        return UserAccount.of(
                userSecurityDto.email(),
                userSecurityDto.password(),
                userSecurityDto.phoneNumber(),
                userSecurityDto.gender(),
                userSecurityDto.age(),
                userSecurityDto.address(),
                userSecurityDto.job(),
                userSecurityDto.userRole()
        );
    }
    public static UserSecurityDto from(UserAccount userAccount) {
        return new UserSecurityDto(
                userAccount.getEmail(),
                userAccount.getPassword(),
                userAccount.getPhoneNumber(),
                userAccount.getGender(),
                userAccount.getAge(),
                userAccount.getAddress(),
                userAccount.getJob(),
                userAccount.getUserRole()
        );
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(List.of(new SimpleGrantedAuthority(userRole.name())));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
