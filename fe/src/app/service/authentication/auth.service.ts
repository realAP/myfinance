import { Injectable, inject } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private router = inject(Router);


  private storageKey = 'authCredentials';  // Define a storage key
  private credentials: string | null = null;

  constructor() {
    const storedCredentials = localStorage.getItem(this.storageKey);
    if (storedCredentials) {
      this.credentials = storedCredentials;
    }
  }

  login(username: string, password: string): void {
    this.credentials = btoa(`${username}:${password}`);  // Store Base64 encoded credentials
    localStorage.setItem(this.storageKey, this.credentials);
  }

  logout(): void {
    console.log('User logged out');
    this.credentials = null;
    localStorage.removeItem(this.storageKey);
    this.router.navigate(['login']);
  }

  isAuthenticated(): boolean {
    const storedCredentials = localStorage.getItem(this.storageKey);
    if (storedCredentials) {
      this.credentials = storedCredentials;
    }
    return this.credentials !== null;
  }

  getAuthorizationHeader(): string | null {
    if (this.credentials) {
      return `Basic ${this.credentials}`;
    }
    return null;
  }
}
