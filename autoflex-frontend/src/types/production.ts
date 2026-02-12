export interface ProductionSuggestion {
  productId: number;
  productName: string;
  quantity: number;
  unitPrice: number;
  totalValue: number;
}

export interface ProductionResult {
  suggestions: ProductionSuggestion[];
  totalProductionValue: number;
  materialUtilization: number;
}
