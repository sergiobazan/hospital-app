package com.bazan.hospital.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
            SELECT
                CASE
                    WHEN COUNT(a) > 0 THEN true
                    ELSE false
                END
            FROM Appointment a
            WHERE a.doctor.id = :doctorId
            AND (:start < a.finish AND :finish > a.finish)
            """)
    boolean isOverlapping(
            @Param("doctorId")
            long doctorId,
            @Param("start")
            LocalDateTime start,
            @Param("finish")
            LocalDateTime finish);
}
