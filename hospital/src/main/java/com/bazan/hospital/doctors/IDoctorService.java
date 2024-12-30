package com.bazan.hospital.doctors;

import com.bazan.hospital.doctors.DTOs.CreateDoctorRequest;

public interface IDoctorService {
    Doctor getById(long id) throws Exception;
    Doctor create(CreateDoctorRequest doctorRequest) throws Exception;
}
