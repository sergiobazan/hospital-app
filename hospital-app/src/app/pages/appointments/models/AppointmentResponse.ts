export interface AppointmentResponse {
  id: number
  title: string
  start: Date
  finish: Date
  status: AppointmentStatus
}

export enum AppointmentStatus {
  PENDING = 'PENDING',
  CONFIRMED = 'CONFIRMED',
  COMPLETED = 'COMPLETED',
  CANCELED = 'CANCELED'
}

export interface AppointmentDoctorPatientResponse {
  doctor: {
    id: number
    name: string
    phone: string
    specialty: string
  },
  patient: {
    id: number
    name: string
    birthDate: Date
    phone: string
    sex: string
  }
}