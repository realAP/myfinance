import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {BackendService} from "../../../service/backend/backend";

@Component({
  selector: 'app-space-creation',
  standalone: true,
  imports: [
    Button,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './space-form.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './space-form.scss'
})
export class SpaceForm {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  name: string = "";

  onCreateSpace() {
    this.backendService.createSpace(this.name).subscribe();
    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Space: ' + this.name + ' created!'});
    this.name = "";
  }
}
