package com.gdsc.pikpet.entity.animal;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.Shelter;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "animal_id")
    private Long id;

    @Setter private String imageUrl;
    @Setter @Enumerated(EnumType.STRING) private Species species;

    @Setter private int age;
    @Setter @Enumerated(EnumType.STRING) private Gender gender;

    @Setter @Enumerated(EnumType.STRING) private Breed breed;
    @Setter @Enumerated(EnumType.STRING) private AnimalSize size;
    @Setter private String disease;

    @Setter @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "shelter_id") private Shelter shelter;
    @Setter private boolean isNeutralized;
    @Setter private boolean checkUp;

    @Setter
    private LocalDateTime captureDate;

    @Setter private LocalDateTime enthanasiaDate;

    @Setter @OneToMany(mappedBy = "animal") List<AnimalColor> animalColors = new ArrayList<>();
}
