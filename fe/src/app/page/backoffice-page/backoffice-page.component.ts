import {Component} from '@angular/core';
import {CardModule} from "primeng/card";
import {BankCreationComponent} from "../../component/bank-creation/bank-creation.component";
import {CategoryCreationComponent} from "../../component/category-creation/category-creation.component";
import {RuleCreationComponent} from "../../component/rule-creation/rule-creation.component";
import {TransferCreationComponent} from "../../component/transfer-creation/transfer-creation.component";
import {SpaceCreationComponent} from "../../component/space-creation/space-creation.component";
import {SpendingCreationComponent} from "../../component/spending-creation/spending-creation.component";
import {ConnectedSquaresComponent} from "../../component/connected-squares/connected-squares.component";

@Component({
  selector: 'app-backoffice-page',
  standalone: true,
  imports: [
    BankCreationComponent,
    CategoryCreationComponent,
    RuleCreationComponent,
    TransferCreationComponent,
    SpaceCreationComponent,
    SpendingCreationComponent,
    CardModule,
    ConnectedSquaresComponent
  ],
  templateUrl: './backoffice-page.component.html',
  styleUrl: './backoffice-page.component.scss'
})
export class BackofficePageComponent {

}
