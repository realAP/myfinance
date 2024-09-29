import {Component} from '@angular/core';
import {AuthService} from "../../service/authentication/auth.service";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  onSubmit() {
    this.authService.login(this.username, this.password);
    console.log('Logged in successfully');
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['spendings']);  // Redirect to the overview page after successful login
      console.log('Logged in successfully and redirected to overview');
    } else {
      console.log('Login failed');
      alert('Invalid credentials. Please try again.');
    }
  }
}
