package com.bazan.hospital.hospitals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHospitalRepository extends JpaRepository<Hospital, Long> {
}
