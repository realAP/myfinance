import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import {Button} from "primeng/button";
import {DatePickerModule} from "primeng/datepicker";
import {SelectModule} from "primeng/select";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {BackendService} from "../../service/backend/backend";


@Component({
  selector: 'app-bank-creation',
  standalone: true,
  imports: [
    Button,
    DatePickerModule,
    SelectModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule,
    ToastModule
  ],
  templateUrl: './bank-creation.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './bank-creation.scss'
})
export class BankCreation {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  name: string = "";

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
