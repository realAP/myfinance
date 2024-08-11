import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-category-creation',
  standalone: true,
    imports: [
        Button,
        FloatLabelModule,
        InputTextModule
    ],
  templateUrl: './category-creation.component.html',
  styleUrl: './category-creation.component.scss'
})
export class CategoryCreationComponent {

}
