package com.bazan.hospital.hospitals;

import com.bazan.hospital.hospitals.DTOs.CreateHospitalRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IHospitalService {
    Hospital getById(long id) throws Exception;
    Hospital create(CreateHospitalRequest hospitalRequest, MultipartFile file) throws Exception;
    void update(long id, CreateHospitalRequest hospitalRequest, MultipartFile logo) throws Exception;
}
