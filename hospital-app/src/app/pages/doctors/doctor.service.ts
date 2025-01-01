import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'src/app/shared/Response';
import { environment } from 'src/environments/environment';
import { Doctor, Specialty } from './models/Doctor';
import { DoctorRequest } from './models/DoctorRequest';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private url = `${environment.apiUrl}/doctors`

  constructor(private http: HttpClient) { }

  getDoctorById(id: number) {
    return this.http.get<Response<Doctor>>(`${this.url}/${id}`);
  }

  createDoctor(doctor: DoctorRequest) {
    return this.http.post<Response<Doctor>>(this.url, doctor);
  }

  getAllDoctors() {
    return this.http.get<Doctor[]>(this.url);
  }

  getSpecialties() {
    return this.http.get<Specialty[]>(`${this.url}/specialties`)
  }
}
