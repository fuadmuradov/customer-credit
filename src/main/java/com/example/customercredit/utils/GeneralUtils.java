package com.example.customercredit.utils;

import com.example.customercredit.model.ModelForCompare;
import com.example.customercredit.model.RefinanceModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            BigDecimal sumDueAmount = BigDecimal.ZERO;
            BigDecimal sumOfNextInstallmentAmount = BigDecimal.ZERO;
            Integer countOfTypeIsNonAbb=0;

            for (RefinanceModel model : combinations){
                sumDueAmount = sumDueAmount.add(model.getDueAmount());
                sumOfNextInstallmentAmount = sumOfNextInstallmentAmount.add(model.getNextInstallmentAmount());
                if(TypeIsNonAbb(model)) countOfTypeIsNonAbb++;
            }

            ModelForCompare modelForCompare = getModelForCompare(sumDueAmount, sumOfNextInstallmentAmount,
                    countOfTypeIsNonAbb, combinations);

            listOfModelCompare.add(modelForCompare);
        }
        Collections.sort(listOfModelCompare, customCompareTwoParameter);

        return listOfModelCompare;
    }

    private Boolean TypeIsNonAbb(RefinanceModel model){
        return !model.getType().equalsIgnoreCase("ABB");

    }

    private ModelForCompare getModelForCompare(BigDecimal sumDueAmount, BigDecimal sumOfNextInstallment,
                                               Integer CountOfTypeIsAbb, List<RefinanceModel> models){
        return ModelForCompare.builder()
                .sumDueAmount(sumDueAmount)
                .sumNextInstallmentAmount(sumOfNextInstallment)
                .CountOfTypeIsNonAbb(CountOfTypeIsAbb)
                .combination(models)
                .build();
    }
    public static Comparator<ModelForCompare> customCompareTwoParameter = (model1, model2) -> {
        int compareCountTypeIsNonAbb = model2.getCountOfTypeIsNonAbb().compareTo(model1.getCountOfTypeIsNonAbb());
        if (compareCountTypeIsNonAbb != 0) {
            return compareCountTypeIsNonAbb;
        }
        return model1.getSumNextInstallmentAmount().compareTo(model2.getSumNextInstallmentAmount());
    };

}
