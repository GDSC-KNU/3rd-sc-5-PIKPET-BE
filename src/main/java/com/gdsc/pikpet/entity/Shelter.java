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
    private int id;

    @Setter @Column(columnDefinition = "GEOMETRY") private Point location;
    @Setter private String branchName;
    @Setter private String contact;
    // Todo: mangerID의 경우 리팩토링이 필요할 수 있음. OneToOne으로 연결시 문제 가능성. ManyToOne으로 변경 고려
}
