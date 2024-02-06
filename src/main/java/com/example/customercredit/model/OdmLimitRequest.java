package com.example.customercredit.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OdmLimitRequest implements Serializable {
    private Integer cif;
    private String fin;
    private Date applicationDate;
    private String channel;
    private BigDecimal calculatedGrossSalary;
    private List<RefinanceModel> customerLoanList; // {10 -> 4 qaliq amount 500< out of scope, amount en cox olanlar }
    private Boolean refinancingSelected;
    private List<RefinanceModel> refinancingSelectedLoans;
    private Boolean isCrossSale;

    private List<List<RefinanceModel>> combinationOfLoans;
}
