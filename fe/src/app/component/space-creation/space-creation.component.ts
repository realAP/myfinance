import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-space-creation',
  standalone: true,
    imports: [
        Button,
        FloatLabelModule,
        InputTextModule
    ],
  templateUrl: './space-creation.component.html',
  styleUrl: './space-creation.component.scss'
})
export class SpaceCreationComponent {

}
