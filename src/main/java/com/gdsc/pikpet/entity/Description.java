package com.gdsc.pikpet.entity;

import com.gdsc.pikpet.entity.animal.Animal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Description {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "description_id")
    private Long id;

    @Setter @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinColumn(name = "animal_id")
    private Animal animal;
    @Setter private String content;
    @Setter private String name;
}
