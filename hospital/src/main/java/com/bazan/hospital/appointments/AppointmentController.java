package com.bazan.hospital.appointments;

import com.bazan.hospital.appointments.DTOs.CreateAppointmentRequest;
import com.bazan.hospital.appointments.DTOs.CreateAppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping("{id}")
    public ResponseEntity<CreateAppointmentResponse> getById(
            @PathVariable("id") long id
    ) {
        try {
            var appointment = appointmentService.getById(id);
            var response = CreateAppointmentResponse.Success(appointment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> create(
            @RequestBody CreateAppointmentRequest request
    ) {
        try {
            var appointment = appointmentService.create(request);
            var response = CreateAppointmentResponse.Success(appointment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = CreateAppointmentResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
