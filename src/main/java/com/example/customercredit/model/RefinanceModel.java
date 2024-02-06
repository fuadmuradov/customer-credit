package com.example.customercredit.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class RefinanceModel implements Serializable {
    private BigDecimal amount;
    private BigDecimal dueAmount; // qaliq amount
    private BigDecimal nextInstallmentAmount;  // bu ayliq odenishdir
    private BigDecimal interest;
    private String currency;
    private String creditType;
    private String type; // ABB or NON_ABB
    private Boolean isRefinancingValidated = true;

}
