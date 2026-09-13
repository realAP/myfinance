import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {BackendService} from "../../service/backend/backend.service";

@Component({
  selector: 'app-category-creation',
  standalone: true,
  imports: [
    Button,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './category-creation.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './category-creation.component.scss'
})
export class CategoryCreationComponent {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  name: string = "";

  onCreateCategory() {
    this.backendService.createCategory(this.name).subscribe();
    this.messageService.add({severity:'success', summary:'Category', detail: this.name});
    this.name = "";
  }

}
