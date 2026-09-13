import {Component, inject, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {TabsModule} from "primeng/tabs";
import {RouterLink} from "@angular/router";
import {AuthService} from "../../service/authentication/auth.service";

/** Eigenes Navigationsmodell statt PrimeNGs MenuItem: route und command sind
 *  dort nur ueber eine Indexsignatur erreichbar, was im Template nicht typsicher ist. */
export interface NavItem {
  label: string;
  icon: string;
  route?: string;
  command?: () => void;
}

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    TabsModule
  ],
  templateUrl: './navbar.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {
  items: NavItem[] = [];

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
