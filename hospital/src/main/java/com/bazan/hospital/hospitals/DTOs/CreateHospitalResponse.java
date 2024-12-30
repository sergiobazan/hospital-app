package com.bazan.hospital.hospitals.DTOs;

import com.bazan.hospital.hospitals.Hospital;
import com.bazan.hospital.shared.DTOs.Response;

public class CreateHospitalResponse extends Response<Hospital> {

    public CreateHospitalResponse(boolean success, String message, Hospital data) {
        super(success, message, data);
    }

    public static CreateHospitalResponse Success(Hospital data) {
        return new CreateHospitalResponse(true, "Hospital Created Successfully", data);
    }

    public static CreateHospitalResponse Failure(String message) {
        return new CreateHospitalResponse(false, message, null);
    }
}
