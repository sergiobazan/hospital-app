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

    private String title;
    private LocalDateTime start;
    private LocalDateTime finish;

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

    private Appointment(
            String title,
            LocalDateTime start,
            LocalDateTime finish,
            AppointmentStatus status,
            Doctor doctor,
            Patient patient
    ) {
        this.title = title;
        this.start = start;
        this.finish = finish;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public static Appointment Create(String title, LocalDateTime start, LocalDateTime finish, Doctor doctor, Patient patient) {

        if (start.isAfter(finish) || finish.isBefore(start)) {
            throw new IllegalArgumentException("Invalid dates");
        }

        return new Appointment(title, start, finish, AppointmentStatus.PENDING, doctor, patient);
    }
}
