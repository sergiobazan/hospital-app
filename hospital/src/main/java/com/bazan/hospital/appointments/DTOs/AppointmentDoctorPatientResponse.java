package com.bazan.hospital.appointments.DTOs;

import com.bazan.hospital.doctors.DTOs.DoctorDto;
import com.bazan.hospital.patients.DTOS.PatientDto;

public record AppointmentDoctorPatientResponse(DoctorDto doctor, PatientDto patient) {
}
