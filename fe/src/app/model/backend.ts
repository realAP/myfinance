export interface SpendingCategoryBlockDto {
  category: string;
  spendingRowDtos: SpendingRowDto[];
  spendingSumPerCategory: number; // Use appropriate numeric type (e.g., number, BigDecimal, etc.)
  percentageToIncome: number;
}

export interface CategoryCreationDto {
  name: string;
}

export interface CategoryDto {
  id: number;
  name: string;
}

export interface SpendingRowDto {
  id: number; // Use appropriate numeric type (e.g., number, bigint, etc.)
  description: string;
  ruleDescription: string;
  ruleId: number
  transferDescription: string;
  transferId: number;
  amount: number; // Use appropriate numeric type (e.g., number, BigDecimal, etc.)
  category: string;
  categoryId: number;
}

export interface SpaceCreationDto {
  name: string;
}

export interface SpaceDto {
  id: number;
  name: string;
}

export interface BankCreationDto {
  name: string;
}

export interface BankDto {
  id: number;
  name: string;
}

export interface RuleCreationDto {
  description: string;
  dateOfExecution: string;
  fromSpaceId: number;
  toSpaceId: number;
}

export interface RuleDto {
  id: number;
  description: string;
  dateOfExecution: string;
  fromSpaceName: string;
  toSpaceName: string;
  fromSpaceId: number;
  toSpaceId: number;
  amount: number;
  oldAmount: number;
  isChange: boolean;
}

export interface TransferCreationDto {
  description: string;
  dateOfExecution: string;
  fromBankId: number;
  toBankId: number;
}

export interface TransferDto {
  id: number;
  dateOfExecution: string;
  description: string;
  fromBankName: string;
  fromBankNameId: number;
  toBankName: string;
  toBankNameId: number;
  amount: number;
  oldAmount: number;
  isChange: boolean;
}

export interface SpendingCreationDto {
  id?: number;
  categoryId: number;
  description: string;
  amount: number;
  ruleId: number
  transferId: number;
}

export interface SpendingEditDto {
  id: number;
  categoryId: number;
  description: string;
  amount: number;
  ruleId: number
  transferId: number;
}

export interface IncomeCreationDto {
  description: string;
  amount: number;
}

export interface IncomeDto {
  id: number;
  description: string;
  amount: number;
}
