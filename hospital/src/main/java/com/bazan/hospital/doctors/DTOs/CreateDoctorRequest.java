package com.bazan.hospital.doctors.DTOs;

public record CreateDoctorRequest(String name, String phone, long hospitalId, long specialtyId) {
}
