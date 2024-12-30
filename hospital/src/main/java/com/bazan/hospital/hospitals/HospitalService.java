package com.bazan.hospital.hospitals;

import com.bazan.hospital.hospitals.DTOs.CreateHospitalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HospitalService implements IHospitalService {

    private final IHospitalRepository hospitalRepository;

    @Override
    public Hospital getById(long id) throws Exception {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new Exception("Hospital not found"));
    }

    @Override
    public Hospital create(CreateHospitalRequest hospitalRequest) {
        var hospital = Hospital.Create(
                hospitalRequest.name(),
                hospitalRequest.address(),
                hospitalRequest.phone(),
                hospitalRequest.email(),
                hospitalRequest.logo()
        );
        return hospitalRepository.save(hospital);
    }
}
