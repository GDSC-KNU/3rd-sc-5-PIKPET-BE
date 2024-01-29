package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
public class SearchHistory {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Setter @ManyToOne @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Setter private String imageUrl;

    @CreatedDate @Column(nullable = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime searchDate;
}
