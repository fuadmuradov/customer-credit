package com.example.customercredit;

import com.example.customercredit.model.ModelForCompare;
import com.example.customercredit.model.RefinanceModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GeneralUtils {

    //Kombinasiyaların yaradılması
    public List<List<RefinanceModel>> findCombination(List<RefinanceModel> loanList){
        List<List<RefinanceModel>> combinations = new ArrayList<>();
        combine(loanList, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void combine(List<RefinanceModel> loanList, int start, List<RefinanceModel> current, List<List<RefinanceModel>> combinations) {
        combinations.add(new ArrayList<>(current));

        for (int i = start; i < loanList.size(); i++) {
            current.add(loanList.get(i));
            combine(loanList, i + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }

    public List<ModelForCompare> sortForOptimalCombinations(List<List<RefinanceModel>> combinationList){
        List<ModelForCompare> listOfModelCompare = new ArrayList<>();

        for (List<RefinanceModel> combinations : combinationList){
            BigDecimal sumDueAmount = BigDecimal.valueOf(0);
            BigDecimal sumOfNextInstallmentAmount = BigDecimal.valueOf(0);
            Integer countOfTypeIsAbb=0;

            for (RefinanceModel model : combinations){
                sumDueAmount = sumDueAmount.add(model.getDueAmount());
                sumOfNextInstallmentAmount = sumOfNextInstallmentAmount.add(model.getNextInstallmentAmount());
                if(TypeIsNonAbb(model)) countOfTypeIsAbb++;
            }

            ModelForCompare modelForCompare = getModelForCompare(sumDueAmount, sumOfNextInstallmentAmount,
                    countOfTypeIsAbb, combinations);

            listOfModelCompare.add(modelForCompare);
        }
        Collections.sort(listOfModelCompare, ModelForCompare.customCompareTwoParameter);
        return listOfModelCompare;
    }

    private Boolean TypeIsNonAbb(RefinanceModel model){
        if(model.getType().equalsIgnoreCase("ABB")) return false;
        else return true;
    }

    private ModelForCompare getModelForCompare(BigDecimal sumDueAmount, BigDecimal sumOfNextInstallment,
                                               Integer CountOfTypeIsAbb, List<RefinanceModel> models){
        return ModelForCompare.builder()
                .sumDueAmount(sumDueAmount)
                .sumNextInstallmentAmount(sumOfNextInstallment)
                .CountOfTypeIsAbb(CountOfTypeIsAbb)
                .combination(models)
                .build();
    }


}
