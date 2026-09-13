import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {NavbarComponent} from "./component/navbar/navbar";
import {ToastModule} from "primeng/toast";

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, NavbarComponent, ToastModule],
    templateUrl: './app.html',
    changeDetection: ChangeDetectionStrategy.Eager,
    styleUrl: './app.scss'
})
export class AppComponent {
  private router = inject(Router);

  title = 'fe';

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }

}
