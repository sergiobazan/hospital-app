package com.bazan.hospital.doctors.DTOs;

import com.bazan.hospital.doctors.Doctor;
import com.bazan.hospital.shared.DTOs.Response;

public class CreateDoctorResponse extends Response<Doctor> {

    public CreateDoctorResponse(boolean success, String message, Doctor data) {
        super(success, message, data);
    }

    public static CreateDoctorResponse Success(Doctor data) {
        return new CreateDoctorResponse(true, "Doctor Created Successfully", data);
    }

    public static CreateDoctorResponse Failure(String message) {
        return new CreateDoctorResponse(false, message, null);
    }
}
