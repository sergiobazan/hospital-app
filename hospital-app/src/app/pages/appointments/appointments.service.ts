import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'src/app/shared/Response';
import { environment } from 'src/environments/environment';
import { AppointmentResponse } from './models/AppointmentResponse';
import { HospitalResponse } from '../hospital/models/HospitalResponse';
import { Doctor } from '../doctors/models/Doctor';
import { Patient } from '../patients/models/Patient';
import { AppointmentRequest } from './models/AppointmentRequest';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsService {
private url = `${environment.apiUrl}/appointments`

  constructor(private http: HttpClient) { }

  getAppointmentlById(id: number) {
    return this.http.get<Response<HospitalResponse>>(`${this.url}/${id}`);
  }

  getAllAppointment() {
    return this.http.get<AppointmentResponse[]>(this.url);
  }

  createAppointment(appointment: AppointmentRequest) {
    return this.http.post<Response<AppointmentResponse>>(this.url, appointment);
  }

  getAllDoctors() {
    return this.http.get<Doctor[]>(`${this.url}/doctors`);
  }

  getAllPatients() {
    return this.http.get<Patient[]>(`${this.url}/patients`);
  }
}
