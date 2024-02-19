package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

@Entity
@Getter
public class Shelter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "shelter_id")
    private Long id;

    @Setter private Point location;
    @Setter private Double longitude;
    @Setter private Double latitude;
    @Setter private String branchName;
    @Setter private String contact;
}
