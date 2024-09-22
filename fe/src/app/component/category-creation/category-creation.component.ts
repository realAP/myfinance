import {Component} from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {MessageService} from "primeng/api";

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
  styleUrl: './category-creation.component.scss'
})
export class CategoryCreationComponent {

  name: string = "";

  constructor(private backendService: BackendService,
              private messageService: MessageService
  ) {
  }

  onCreateCategory() {
    this.backendService.createCategory(this.name).subscribe({
      error: (error) => {
        this.messageService.add({severity:'error', summary:'Fehler: Konnte create Category nicht erstellen', detail: error.message});
      }
    })
    this.messageService.add({severity:'success', summary:'Category', detail: this.name});
    this.name = "";
  }

}
