import { Component, OnInit } from '@angular/core';
import { DoctorService } from './doctor.service';
import { Doctor } from './models/Doctor';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: []
})
export class DoctorsComponent implements OnInit {
  doctors: Doctor[] = []

  constructor(private service: DoctorService) {}
 
  ngOnInit(): void {
    this.service.getAllDoctors().subscribe({
      next: (response) => {
        this.doctors = response;
      },
      error: ({error}) => console.error(error)
    })
  }
}
