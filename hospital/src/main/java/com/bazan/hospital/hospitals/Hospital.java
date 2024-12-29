package com.bazan.hospital.hospitals;

import com.bazan.hospital.doctors.Doctor;
import com.bazan.hospital.patients.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(unique = true)
    private String address;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    private String logo;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();


}

//private Hospital(String name, String address, String phone, String email, String logo) {
//    this.name = name;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//    this.logo = logo;
//}
//
//public static Hospital Create(String name, String address, String phone, String email, String logo) {
//    return new Hospital(name, address, phone, email, logo);
//}