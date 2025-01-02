package com.bazan.hospital.patients.DTOS;

import java.time.LocalDate;

public record PatientDto(long id, String name, LocalDate birthDate, String phone, String sex) {
}
