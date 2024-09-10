import {Component, OnInit} from '@angular/core';
import {TabMenuModule} from "primeng/tabmenu";
import {MenuItem} from "primeng/api";

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

  constructor() {
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
        label: 'Backoffice',
        icon: 'pi pi-list',
        route: '/backoffice'
      },
    ];
  }

}
