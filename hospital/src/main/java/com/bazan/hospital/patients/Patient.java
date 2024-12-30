package com.bazan.hospital.patients;

import com.bazan.hospital.appointments.Appointment;
import com.bazan.hospital.hospitals.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    private Patient(String name, String phone, Sex sex, LocalDate birthDate, Hospital hospital) {
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.birthDate = birthDate;
        this.hospital = hospital;
    }

    public static Patient Create(String name, String phone, Sex sex, LocalDate birthDate, Hospital hospital) {
        return new Patient(name, phone, sex, birthDate, hospital);
    }
}
