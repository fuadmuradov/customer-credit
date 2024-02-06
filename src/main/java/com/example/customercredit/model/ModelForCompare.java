package com.example.customercredit.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class ModelForCompare  {
    private BigDecimal sumDueAmount;
    private BigDecimal sumNextInstallmentAmount;
    private Integer CountOfTypeIsAbb;
    private List<RefinanceModel> combination;


    public static Comparator<ModelForCompare> customCompareTwoParameter = new Comparator<ModelForCompare>() {
        @Override
        public int compare(ModelForCompare model1, ModelForCompare model2) {
            int compareCountTypeIsAbb = model2.getCountOfTypeIsAbb().compareTo(model1.getCountOfTypeIsAbb());
            if (compareCountTypeIsAbb != 0) {
                return compareCountTypeIsAbb;
            }
            return model2.getSumDueAmount().compareTo(model1.getSumDueAmount());
        }
    };
}
