import {Component, inject, OnInit} from '@angular/core';
import {TabMenuModule} from "primeng/tabmenu";
import {MenuItem} from "primeng/api";
import {AuthService} from "../../service/authentication/auth.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    TabMenuModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {
  items: MenuItem[] | undefined;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Ausgaben',
        icon: 'pi pi-home',
        route: '/spendings',
      },
      {
        label: 'Transfers',
        icon: 'pi pi-building-columns',
        route: '/transfers',
      },
      {
        label: 'Rules',
        icon: 'pi pi-hammer',
        route: '/rules',
      },
      {
        label: 'Einnahmen',
        icon: 'pi pi-plus',
        route: '/incomes'
      },
      {
        label: 'Backoffice',
        icon: 'pi pi-list',
        route: '/backoffice'
      },
      {
        label: 'Logout',
        icon: 'pi pi-power-off',
        command: () => {
          this.authService.logout();

        }
      }
    ];
  }

}
