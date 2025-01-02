import { Component, signal, ChangeDetectorRef, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions, DateSelectArg, EventClickArg, EventApi, EventInput } from '@fullcalendar/core';
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import { AppointmentsService } from './appointments.service';
import { ModalComponent } from 'src/app/components/modal/modal.component';
import { Doctor } from '../doctors/models/Doctor';
import { Patient } from '../patients/models/Patient';
import { AppointmentRequest } from './models/AppointmentRequest';
import { FullCalendarComponent } from '@fullcalendar/angular';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: []
})
export class AppointmentsComponent implements OnInit {
  @ViewChild('calendar') calendarComponent!: FullCalendarComponent;

  calendarVisible = signal(true);
  calendarOptions = signal<CalendarOptions>({
    plugins: [
      interactionPlugin,
      dayGridPlugin,
      timeGridPlugin,
      listPlugin,
    ],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    initialView: 'dayGridMonth',
    initialEvents: [], // alternatively, use the `events` setting to fetch from a feed
    weekends: true,
    editable: true,
    selectable: true,
    selectMirror: true,
    dayMaxEvents: true,
    select: this.handleDateSelect.bind(this),
    eventClick: this.handleEventClick.bind(this),
    eventsSet: this.handleEvents.bind(this)
    /* you can update a remote database when these fire:
    eventAdd:
    eventChange:
    eventRemove:
    */
  });
  currentEvents = signal<EventApi[]>([]);

  doctors: Doctor[] = [];
  patients: Patient[] = [];
  doctorSelected: number = 1;
  patientSelected: number = 1;
  appointmentRequest: AppointmentRequest = {
    title: '',
    start: new Date(),
    finish: new Date(),
  }
  
  @ViewChild('appointment_modal') appointmentModal!: ModalComponent;

  constructor(private service: AppointmentsService, private changeDetector: ChangeDetectorRef) {
  }
  
  ngOnInit(): void {
    this.getAllEvents();
    this.getDoctors();
    this.getPatients();
  }

  handleCalendarToggle() {
    this.calendarVisible.update((bool) => !bool);
  }

  handleWeekendsToggle() {
    this.calendarOptions.mutate((options) => {
      options.weekends = !options.weekends;
    });
  }

  handleDateSelect(selectInfo: DateSelectArg) {
    this.appointmentModal.open();

    const formatDate = (date: Date) => {
      const pad = (num: number) => num.toString().padStart(2, '0');
      const year = date.getFullYear();
      const month = pad(date.getMonth() + 1); // Los meses en JavaScript son base 0
      const day = pad(date.getDate());
      const hours = pad(date.getHours());
      const minutes = pad(date.getMinutes());
  
      return `${year}-${month}-${day}T${hours}:${minutes}`;
    };

    this.appointmentRequest.start = formatDate(selectInfo.start) as any;
    this.appointmentRequest.finish = formatDate(selectInfo.start) as any;
  }

  handleEventClick(clickInfo: EventClickArg) {
    if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      clickInfo.event.remove();
    }
  }

  handleEvents(events: EventApi[]) {
    this.currentEvents.set(events);
    this.changeDetector.detectChanges(); // workaround for pressionChangedAfterItHasBeenCheckedError
  }

  private getAllEvents() {
    this.service.getAllAppointment().subscribe({
      next: (response) => {
        const allEvents: EventInput[] = response.map(appointment => ({
          id: String(appointment.id),
          title: appointment.title,
          start: appointment.start,
          end: appointment.finish
        }));
        this.calendarOptions.mutate((options) => {
          options.events = allEvents;
        })
      },
      error: ({error}) => console.error(error)
    })
  }

  private getDoctors() {
    this.service.getAllDoctors().subscribe({
      next: (response) => this.doctors = response,
      error: ({error}) => console.error(error)
    })
  }

  private getPatients() {
    this.service.getAllPatients().subscribe({
      next: (response) => this.patients = response,
      error: ({error}) => console.error(error)
    })
  }

  protected onSubmit() {
    this.appointmentRequest = {
      ...this.appointmentRequest,
      doctorId: this.doctorSelected,
      patientId: this.patientSelected
    }
    
    this.service.createAppointment(this.appointmentRequest).subscribe({
      next: (response) => {
        if (response.success) {
          this.addEventToCalendar(response.data.id);
          this.appointmentModal.close();
        }
        console.error(response.message)
      },
      error: ({error}) => console.error(error)
    })
  }

  private addEventToCalendar(id: number) {
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.addEvent({
      id: String(id),
      title: this.appointmentRequest.title,
      start: this.appointmentRequest.start,
      end: this.appointmentRequest.finish,
      allDay: false,
      extendedProps: {
        doctorId: this.appointmentRequest.doctorId,
        patientId: this.appointmentRequest.patientId
      }
    });
    calendarApi.unselect();
  }
}
