package com.gdsc.pikpet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class UserAccount {

    @Id @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter @Column(unique = true) private String email;
    @Setter @Column(length = 100, nullable = false) private String password;
    @Setter private String phoneNumber;
    @Setter private Gender gender;
    @Setter private int age;
    @Setter private String address;
    @Setter private String job;
    @Setter @Enumerated(EnumType.STRING) private UserRole userRole;

    @Setter @ManyToOne @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    protected UserAccount(){};

    private UserAccount(String email,String password,String phoneNumber,Gender gender,int age,String address,String job, UserRole userRole){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.job = job;
        this.userRole = userRole;
    }

    public static UserAccount of(String email,String password){
        return new UserAccount(email,password,null,null,0,null,null,UserRole.USER);
    }
    public static UserAccount of(String email, String password, String phoneNumber, Gender gender,int age, String address,String job,UserRole userRole){
        return new UserAccount(email,password,phoneNumber,gender,age,address,job,userRole);
    }

}
