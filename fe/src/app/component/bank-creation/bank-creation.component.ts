import {Component} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {BackendService} from "../../service/backend/backend.service";


@Component({
  selector: 'app-bank-creation',
  standalone: true,
  imports: [
    Button,
    CalendarModule,
    DropdownModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule,
    ToastModule
  ],
  templateUrl: './bank-creation.component.html',
  styleUrl: './bank-creation.component.scss'
})
export class BankCreationComponent {

  name: string = "";

  constructor(private backendService: BackendService,
              private messageService: MessageService
  ) {
  }

  onCreateBank() {
    this.backendService.createBank(this.name).subscribe(
      {
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Bank: ' + this.name + ' created!'
          });
          this.name = "";
        }
      }
    );
  }
}
