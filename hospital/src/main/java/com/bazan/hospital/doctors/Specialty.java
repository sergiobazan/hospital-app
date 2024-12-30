package com.bazan.hospital.doctors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "specialty")
    @JsonIgnore
    private List<Doctor> doctors = new ArrayList<>();

    private Specialty(String name) {
        this.name = name;
    }

    public static Specialty Create(String name) {
        return new Specialty(name);
    }
}
