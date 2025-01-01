import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorsComponent } from './pages/doctors/doctors.component';
import { AppointmentsComponent } from './pages/appointments/appointments.component';
import { PatientsComponent } from './pages/patients/patients.component';
import { HospitalComponent } from './pages/hospital/hospital.component';

const routes: Routes = [
  {
    path: "doctors",
    component: DoctorsComponent
  },
  {
    path: "appointments",
    component: AppointmentsComponent
  },
  {
    path: "patients",
    component: PatientsComponent
  },
  {
    path: "hospital",
    component: HospitalComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
