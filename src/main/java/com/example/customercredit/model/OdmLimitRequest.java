package com.example.customercredit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OdmLimitRequest implements Serializable {
    private Integer cif;
    private String fin;
    private Date applicationDate;
    private String channel;
    private BigDecimal calculatedGrossSalary;
    private List<RefinanceModel> customerLoanList; //
    private Boolean refinancingSelected;
    private List<RefinanceModel> refinancingSelectedLoans;
    private Boolean isCrossSale;
    private List<List<RefinanceModel>> combinationOfLoans;
}
