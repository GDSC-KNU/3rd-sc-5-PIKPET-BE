package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class UserLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter @ManyToOne @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Setter @ManyToOne @JoinColumn(name = "animalId")
    private Animal animal;
}
