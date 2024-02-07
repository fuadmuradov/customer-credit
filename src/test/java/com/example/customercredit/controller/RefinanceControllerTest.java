package com.example.customercredit.controller;

import com.example.customercredit.model.OdmLimitRequest;
import com.example.customercredit.model.RefinanceModel;
import com.example.customercredit.service.RefinanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.customercredit.TestConstants.ODM_LIMIT_REQ;
import static com.example.customercredit.TestConstants.REFINANCE_MODEL;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RefinanceControllerTest {
    @InjectMocks
    private RefinanceController refinanceController;
    @Mock
    private RefinanceService refinanceService;

    @Test
    public void testRefinance() {

        List<RefinanceModel> models = new ArrayList<>();
        var refinance_model1 = REFINANCE_MODEL;
        var refinance_model2 = REFINANCE_MODEL;
        var refinance_model3 = REFINANCE_MODEL;
        refinance_model1.setType("ABB");
        refinance_model1.setDueAmount(BigDecimal.valueOf(4500));
        refinance_model1.setNextInstallmentAmount(BigDecimal.valueOf(320));
        refinance_model2.setType("NON_ABB");
        refinance_model2.setDueAmount(BigDecimal.valueOf(1500));
        refinance_model2.setNextInstallmentAmount(BigDecimal.valueOf(120));
        refinance_model3.setType("NON_ABB");
        refinance_model3.setDueAmount(BigDecimal.valueOf(2500));
        refinance_model3.setNextInstallmentAmount(BigDecimal.valueOf(220));
        models.add(refinance_model1);
        models.add(refinance_model2);
        models.add(refinance_model3);

        OdmLimitRequest request = ODM_LIMIT_REQ;
        ODM_LIMIT_REQ.setCustomerLoanList(models);

        models.remove(0);
        OdmLimitRequest response = ODM_LIMIT_REQ;
        response.setRefinancingSelectedLoans(models);

        when(refinanceService.refinancedCombination(request)).thenReturn(response);
        ResponseEntity<OdmLimitRequest> result = refinanceController.refinance(request);
        ResponseEntity<OdmLimitRequest> waitingResponse = new ResponseEntity<>(response,
                HttpStatusCode.valueOf(200));
        Assertions.assertEquals(waitingResponse.getStatusCode(), result.getStatusCode());
        Assertions.assertEquals(waitingResponse.getBody(), result.getBody());

    }
}
