import { Component, OnInit } from '@angular/core';
import { HospitalService } from './hospital.service';

@Component({
  selector: 'app-hospital',
  templateUrl: './hospital.component.html',
  styleUrls: []
})
export class HospitalComponent implements OnInit {

  constructor(private service: HospitalService) {}
  
  ngOnInit(): void {
    this.service.getHospitalById(1).subscribe({
      next: (response) => {
        console.log(response.data);
      },
      error: ({ message }) => console.error(message)
    })
  }
}
