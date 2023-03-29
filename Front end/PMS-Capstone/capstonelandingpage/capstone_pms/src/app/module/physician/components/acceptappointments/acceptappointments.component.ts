import { PhysicianService } from './../../physician.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component } from '@angular/core';

export class AcceptedEmailNotification {
  fromEmail: any;
  toEmail: any;
  subject: any;
  body: any;
}

@Component({
  selector: 'app-acceptappointments',
  templateUrl: './acceptappointments.component.html',
  styleUrls: ['./acceptappointments.component.scss'],
})
export class AcceptappointmentsComponent {
  http: any;
  constructor(
    private snakBar: MatSnackBar,
    private service: PhysicianService
  ) {}

  data: any;
  appointmentId: any = sessionStorage.getItem('appointmentid');
  Accept() {
    this.service
      .acceptappointment(this.appointmentId, 'accepted')
      .subscribe((response) => {
        this.data = response;
      });
  }

  openAcceptSnackbar(message: string, action: string) {
    let snakBarRef = this.snakBar.open(message, action, { duration: 3000 });
    snakBarRef.afterDismissed().subscribe();

    snakBarRef.onAction().subscribe();
  }
  acceptedEmailNotification: AcceptedEmailNotification =
    new AcceptedEmailNotification();

  sendAcceptedMail() {
    const url = 'http://localhost:8080/sendEmail';
    const body = {
      fromEmail: 'krunalzodape9@gmail.com',
      toEmail: 'aakashsolanke99@gmail.com',
      subject: 'Your Appoitnment Accepted',
      body: 'Hey this is new mail, send from Angular accepted button',
    };
    this.http.post(url, body).subscribe(
      () => alert('Email sent successfully!'),
      (error: any) => console.error(error)
    );
    console.log('mail sended ', body);
  }
}
