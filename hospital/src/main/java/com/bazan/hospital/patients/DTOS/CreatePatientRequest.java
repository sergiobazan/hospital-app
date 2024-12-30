package com.bazan.hospital.patients.DTOS;

import com.bazan.hospital.patients.Sex;

import java.time.LocalDate;

public record CreatePatientRequest(String name, String phone, Sex sex, LocalDate birthDate, long hospitalId) {
}
