import {Component} from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-space-creation',
  standalone: true,
  imports: [
    Button,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './space-form.component.html',
  styleUrl: './space-form.component.scss'
})
export class SpaceFormComponent {

  name: string = "";

  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  onCreateSpace() {
    this.backendService.createSpace(this.name).subscribe(
      {
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Could not create space.'});
        }
      }
    );
    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Space: ' + this.name + ' created!'});
    this.name = "";
  }
}
