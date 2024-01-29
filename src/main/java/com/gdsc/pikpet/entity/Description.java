package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Description {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinColumn(name = "animalId")
    private Animal animal;
    @Setter private String content;
    @Setter private String name;
}
