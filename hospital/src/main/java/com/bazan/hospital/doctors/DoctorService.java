package com.bazan.hospital.doctors;

import com.bazan.hospital.doctors.DTOs.CreateDoctorRequest;
import com.bazan.hospital.hospitals.IHospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final IHospitalRepository hospitalRepository;
    private final ISpecialtyRepository specialtyRepository;

    @Override
    public Doctor getById(long id) throws Exception {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new Exception("Doctor not found"));
    }

    @Override
    public Doctor create(CreateDoctorRequest doctorRequest) throws Exception {
        var hospital = hospitalRepository
                .findById(doctorRequest.hospitalId())
                .orElseThrow(() -> new Exception("Hospital not found"));

        var specialty = specialtyRepository
                .findById(doctorRequest.specialtyId())
                .orElseThrow(() -> new Exception("Specialty not found"));

        var doctor = Doctor.Create(
                doctorRequest.name(),
                doctorRequest.phone(),
                hospital,
                specialty
        );

        return doctorRepository.save(doctor);
    }
}
