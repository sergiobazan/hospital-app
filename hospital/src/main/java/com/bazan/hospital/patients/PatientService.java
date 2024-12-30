package com.bazan.hospital.patients;

import com.bazan.hospital.hospitals.IHospitalRepository;
import com.bazan.hospital.patients.DTOS.CreatePatientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;
    private final IHospitalRepository hospitalRepository;

    @Override
    public Patient getById(long id) throws Exception {
        return patientRepository.findById(id)
                .orElseThrow(() -> new Exception("Patient not found"));
    }

    @Override
    public Patient create(CreatePatientRequest patientRequest) throws Exception {
        var hospital = hospitalRepository
                .findById(patientRequest.hospitalId())
                .orElseThrow(() -> new Exception("Hospital not found"));

        var patient = Patient.Create(
                patientRequest.name(),
                patientRequest.phone(),
                patientRequest.sex(),
                patientRequest.birthDate(),
                hospital
        );

        return patientRepository.save(patient);
    }
}
