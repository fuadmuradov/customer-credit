package com.example.customercredit.service;

import com.example.customercredit.model.ModelForCompare;
import com.example.customercredit.model.RefinanceModel;
import com.example.customercredit.utils.GeneralUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.customercredit.TestConstants.ODM_LIMIT_REQ;
import static com.example.customercredit.TestConstants.REFINANCE_MODEL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefinanceServiceTest {
    @Mock
    private GeneralUtils generalUtils;

    @InjectMocks
    private RefinanceService refinanceService;



    @Test
    void refinancedCombination() {
        List<List<RefinanceModel>> sortedCombinations = new ArrayList<>();
        var refModel1 = REFINANCE_MODEL;
//        refModel1.setDueAmount(BigDecimal.ONE);
        refModel1.setType("ABB");
        var refModel2 = REFINANCE_MODEL;
//        refModel2.setDueAmount(BigDecimal.TEN);
        refModel2.setType("Non_Abb");
        ModelForCompare MODEL_FOR_COMP = ModelForCompare.builder()
//            .sumDueAmount()
                .build();

        ModelForCompare MODEL_FOR_COMP2 = ModelForCompare.builder()
//            .sumDueAmount()
                .build();

        List<RefinanceModel> customerLoanList = Arrays.asList(refModel1, refModel2);
        var combinations = List.of(customerLoanList);
        MODEL_FOR_COMP.setCombination(customerLoanList);
        MODEL_FOR_COMP2.setCombination(customerLoanList);
        List<ModelForCompare> modelForCompareList = Arrays.asList(MODEL_FOR_COMP, MODEL_FOR_COMP2);
        ODM_LIMIT_REQ.setRefinancingSelectedLoans(modelForCompareList.get(0).getCombination());
        ODM_LIMIT_REQ.setCustomerLoanList(customerLoanList);
//        modelForCompareList.remove(0);
        sortedCombinations.add(customerLoanList);
        sortedCombinations.add(customerLoanList);
        ODM_LIMIT_REQ.setCombinationOfLoans(sortedCombinations);

        when(generalUtils.findCombination(customerLoanList)).thenReturn(combinations);
        when(generalUtils.sortForOptimalCombinations(combinations)).thenReturn(modelForCompareList);

        var actual = refinanceService.refinancedCombination(ODM_LIMIT_REQ);
        var expected = ODM_LIMIT_REQ;

        Assertions.assertEquals(actual, expected);
    }

}