package com.bazan.hospital.hospitals;

import com.bazan.hospital.hospitals.DTOs.CreateHospitalRequest;

public interface IHospitalService {
    Hospital getById(long id) throws Exception;
    Hospital create(CreateHospitalRequest hospitalRequest);
}
