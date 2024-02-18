package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, name = "application_id")
    private Long id;

    @Setter @ManyToOne @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Setter @ManyToOne @JoinColumn(name = "animal_id")
    private Animal animal;

    @CreatedDate @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Setter private String contents;

    private Application() {
    }
    private Application(UserAccount userAccount, Animal animal, String contents) {
        this.userAccount = userAccount;
        this.animal = animal;
        this.contents = contents;
    }
    public static Application of(UserAccount userAccount, Animal animal, String contents) {
        return new Application(userAccount, animal, contents);
    }
}
