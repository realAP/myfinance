import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";

@Component({
  selector: 'app-space-creation',
  standalone: true,
  imports: [
    Button,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './space-creation.component.html',
  styleUrl: './space-creation.component.scss'
})
export class SpaceCreationComponent {

  name: string = "";

  constructor(private backendService: BackendService) {
  }

  onCreateSpace() {
    this.backendService.createSpace(this.name);
    this.name = "";
  }
}
