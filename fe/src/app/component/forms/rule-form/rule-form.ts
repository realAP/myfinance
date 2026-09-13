import { Component, OnInit, ChangeDetectionStrategy, inject, input, output } from '@angular/core';
import {Button} from "primeng/button";
import {SelectModule} from "primeng/select";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {InputGroupModule} from "primeng/inputgroup";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {DatePickerModule} from "primeng/datepicker";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {RuleCreationDto, SpaceDto} from "../../../model/backend";
import {BackendService} from "../../../service/backend/backend";

export interface RuleFormDto {
  ruleCreationDto: RuleCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-rule-form',
  standalone: true,
  imports: [
    Button,
    SelectModule,
    FloatLabelModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    InputTextModule,
    DatePickerModule,
    FormsModule
  ],
  templateUrl: './rule-form.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './rule-form.scss'
})
export class RuleFormComponent implements OnInit {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);

  date: any;
  spaces: SpaceDto[] = [];
  selectedFromSpace: SpaceDto = {} as SpaceDto;
  selectedTargetSpace: SpaceDto = {} as SpaceDto;
  name: string = "";

  readonly preFilledRuleFormDto = input<RuleFormDto>();
  readonly formSubmit = output<RuleCreationDto>();

  ngOnInit(): void {
    this.backendService.getSpaces().subscribe(spaceDtos => {
      this.spaces = spaceDtos;

      const preFilledRuleFormDto = this.preFilledRuleFormDto();
      if (preFilledRuleFormDto?.isPreFilled) {

        const preFilledData = preFilledRuleFormDto?.ruleCreationDto;

        this.selectedFromSpace = this.spaces.find(space => space.id === preFilledData?.fromSpaceId)!;
        this.selectedTargetSpace = this.spaces.find(space => space.id === preFilledData?.toSpaceId)!;
        this.date = new Date(preFilledData.dateOfExecution);
        this.name = preFilledData.description;
      }
    });
  }

  onClick() {
    if (!this.selectedFromSpace.id || !this.selectedTargetSpace.id || !this.date || !this.name) {
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please fill in all fields'});
      return;
    }
    const ruleCreationDto: RuleCreationDto = {
      description: this.name,
      dateOfExecution: this.date.toLocaleDateString("en-CA"),
      fromSpaceId: this.selectedFromSpace.id,
      toSpaceId: this.selectedTargetSpace.id,
    }

    this.formSubmit.emit(ruleCreationDto);
  }
}
