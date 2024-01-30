package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "animal_id")
    private int id;

    @Setter private String imageUrl;
    @Setter @Enumerated(EnumType.STRING) private AnimalType animalType;

    @Setter private int age;
    @Setter @Enumerated(EnumType.STRING) private Gender gender;

    @Setter private String species;
    @Setter @Enumerated(EnumType.STRING) private AnimalSize size;
    @Setter private String disease;

    @Setter @ManyToOne @JoinColumn(name = "shelter_id") private Shelter shelter;
    @Setter private boolean isNeutralized;
    @Setter private boolean checkUp;

    @Setter @CreatedDate @Column(nullable = false)
    private LocalDateTime captureDate;

    @Setter private LocalDateTime enthanasiaDate;

    @Setter @ElementCollection(fetch = FetchType.LAZY,targetClass = String.class) private List<String> color;
}
