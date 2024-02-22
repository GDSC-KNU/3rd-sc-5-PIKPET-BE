package com.gdsc.pikpet.entity;

import com.gdsc.pikpet.entity.animal.Animal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "user_like", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"user_account_id", "animal_id"}
        )
})
public class UserLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_like_id")
    private Long id;

    @Setter @ManyToOne @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Setter @ManyToOne @JoinColumn(name = "animal_id")
    private Animal animal;

    private UserLike(UserAccount userAccount, Animal animal){
        this.userAccount = userAccount;
        this.animal = animal;
    }
    private UserLike(){};

    public static UserLike of(UserAccount userAccount, Animal animal){
        return new UserLike(userAccount,animal);
    }
}
