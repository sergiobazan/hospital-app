package com.bazan.hospital.patients.DTOS;

import com.bazan.hospital.patients.Patient;
import com.bazan.hospital.shared.DTOs.Response;

public class CreatePatientResponse extends Response<Patient> {

    public CreatePatientResponse(boolean success, String message, Patient data) {
        super(success, message, data);
    }

    public static CreatePatientResponse Success(Patient data) {
        return new CreatePatientResponse(true, "Patient Created Successfully", data);
    }

    public static CreatePatientResponse Failure(String message) {
        return new CreatePatientResponse(false, message, null);
    }
}
