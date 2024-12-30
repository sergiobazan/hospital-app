package com.bazan.hospital.appointments;

import com.bazan.hospital.doctors.Doctor;
import com.bazan.hospital.patients.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    private Appointment(LocalDateTime date, AppointmentStatus status, Doctor doctor, Patient patient) {
        this.date = date;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public static Appointment Create(LocalDateTime date, Doctor doctor, Patient patient) {
        return new Appointment(date, AppointmentStatus.PENDING, doctor, patient);
    }
}
