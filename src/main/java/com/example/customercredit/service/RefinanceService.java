package com.example.customercredit.service;

import com.example.customercredit.utils.GeneralUtils;
import com.example.customercredit.model.ModelForCompare;
import com.example.customercredit.model.OdmLimitRequest;
import com.example.customercredit.model.RefinanceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefinanceService {
    private final GeneralUtils generalUtils;
    public OdmLimitRequest refinancedCombination(OdmLimitRequest request){

        //Bütün kombinasiyaların tapılması = 2^n(2 ^ getCustomerLoanList().size())
        List<List<RefinanceModel>> combinations = generalUtils
                .findCombination(request.getCustomerLoanList());

        // Combinasiyalar optimal variantlar üzrə sıralanır.
        // Ən Optimal varian sıralanmış listin ilk elementidir.
        // Ən Optimal variantı tapmaq üçün combinasiyalar daxilində sumOfNextInstallmentAmount və non_ABB olan kredit Type-ların sayı
        // tapılır. Öncəlik non_ABB tiplərə verildiyi üçün əvvəlcə tapılan non_ABB tiplərinin sayına görə
        // əgər bir neçə kombinasiyada eyni sayda non_ABB type varsa aylıq ödənilməli olan
        // məbləğlərin(sumOfNextInstallmentAmount az olan optimal variant seçilir) cəmi müqayisə olunur.
        List<ModelForCompare> modelForCompareList = generalUtils.sortForOptimalCombinations(combinations);

        // Ən Optimal variant listin ilk elementi olduğuna görə setRefinancingSelectedLoans parametrinə mənimsədilir.
        request.setRefinancingSelectedLoans(modelForCompareList.get(0).getCombination());
        // Təkrar eyni kombinasiyanın təkrarlanmaması üçün listdən ilk element silinir.
     //   modelForCompareList.remove(0);

        // RefinanceModel bir modeldir hansı ki, kombinasiyalarda olan sumDueAmount və CountofTypeİsAbb saylarını
        // istifadə etməkdə yardımçı olur
        List<List<RefinanceModel>> sortedCombinations = new ArrayList<>();

        for (ModelForCompare model : modelForCompareList){
            sortedCombinations.add(model.getCombination());
        }
        // kombinasiylar bizə gələn request-ə əlavə edilərək geri göndərilir
        request.setCombinationOfLoans(sortedCombinations);

        return request;
    }

}
