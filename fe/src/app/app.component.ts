import { Component, ChangeDetectionStrategy } from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {NavbarComponent} from "./component/navbar/navbar.component";
import {ToastModule} from "primeng/toast";

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, NavbarComponent, ToastModule],
    templateUrl: './app.component.html',
    changeDetection: ChangeDetectionStrategy.Eager,
    styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'fe';

  constructor(private router: Router) {}

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }

}
