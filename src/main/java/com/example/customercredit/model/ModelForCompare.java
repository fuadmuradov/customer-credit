package com.example.customercredit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ModelForCompare  {
    private BigDecimal sumDueAmount;
    private BigDecimal sumNextInstallmentAmount;
    private Integer CountOfTypeIsNonAbb;
    private List<RefinanceModel> combination;

}
