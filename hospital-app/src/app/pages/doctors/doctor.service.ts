import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'src/app/shared/Response';
import { environment } from 'src/environments/environment';
import { Doctor } from './models/Doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private url = `${environment.apiUrl}/doctors`

  constructor(private http: HttpClient) { }

  getDoctorById(id: number) {
    return this.http.get<Response<any>>(`${this.url}/${id}`);
  }

  createDoctor(doctor: any) {
    return this.http.post<Response<any>>(this.url, doctor);
  }

  getAllDoctors() {
    return this.http.get<Doctor[]>(this.url);
  }
}
