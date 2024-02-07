package com.example.customercredit;

import com.example.customercredit.model.OdmLimitRequest;
import com.example.customercredit.model.RefinanceModel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConstants {
    public static RefinanceModel REFINANCE_MODEL = RefinanceModel.builder()
            .isRefinancingValidated(true)
            .currency("AZN")
            .amount(BigDecimal.TEN)
            .build();

    public static OdmLimitRequest ODM_LIMIT_REQ = OdmLimitRequest.builder()
            .fin("432532")
            .cif(121212)
            .applicationDate(new Date())
            .channel("channel_e29985d938f2")
            .calculatedGrossSalary(BigDecimal.TEN)
            .build();

}
