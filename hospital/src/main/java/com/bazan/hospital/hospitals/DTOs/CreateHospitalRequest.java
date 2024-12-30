package com.bazan.hospital.hospitals.DTOs;

public record CreateHospitalRequest(String name, String address, String phone, String email, String logo) {
}
