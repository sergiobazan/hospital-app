package com.bazan.hospital.doctors;

import com.bazan.hospital.appointments.Appointment;
import com.bazan.hospital.hospitals.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    private Doctor(String name, String phone, Hospital hospital, Specialty specialty) {
        this.name = name;
        this.phone = phone;
        this.hospital = hospital;
        this.specialty = specialty;
    }

    public static Doctor Create(String name, String phone, Hospital hospital, Specialty specialty) {
        return new Doctor(name, phone, hospital, specialty);
    }
}
