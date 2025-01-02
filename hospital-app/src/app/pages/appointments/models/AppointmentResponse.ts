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