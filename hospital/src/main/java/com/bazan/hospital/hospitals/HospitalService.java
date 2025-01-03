package com.bazan.hospital.hospitals;

import com.bazan.hospital.config.IAmazonS3Service;
import com.bazan.hospital.hospitals.DTOs.CreateHospitalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class HospitalService implements IHospitalService {

    private final IHospitalRepository hospitalRepository;
    private final IAmazonS3Service amazonS3Service;

    @Override
    public Hospital getById(long id) throws Exception {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new Exception("Hospital not found"));
    }

    @Transactional
    @Override
    public Hospital create(CreateHospitalRequest hospitalRequest, MultipartFile file) throws Exception {
        String logoUrl = amazonS3Service.uploadFileUrl(file);

        var hospital = Hospital.Create(
                hospitalRequest.name(),
                hospitalRequest.address(),
                hospitalRequest.phone(),
                hospitalRequest.email(),
                logoUrl
        );
        return hospitalRepository.save(hospital);
    }

    @Override
    public void update(long id, CreateHospitalRequest hospitalRequest, MultipartFile logo) throws Exception {
        var hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new Exception("Hospital not found"));

        String logoUrl = hospital.getLogo();

        if (!logo.isEmpty()) {
            logoUrl = amazonS3Service.uploadFileUrl(logo);
        }
        hospital.setName(hospitalRequest.name());
        hospital.setEmail(hospitalRequest.email());
        hospital.setAddress(hospitalRequest.address());
        hospital.setPhone(hospitalRequest.phone());
        hospital.setLogo(logoUrl);

        hospitalRepository.save(hospital);
    }
}
