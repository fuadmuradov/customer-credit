package com.example.customercredit.controller;

import com.example.customercredit.model.OdmLimitRequest;
import com.example.customercredit.model.RefinanceModel;
import com.example.customercredit.service.RefinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refinance")
@RequiredArgsConstructor
public class RefinanceController {
    private final RefinanceService refinanceService;

    @PostMapping
    public ResponseEntity<OdmLimitRequest> refinance(@RequestBody OdmLimitRequest request){
         return  ResponseEntity.ok(refinanceService.refinancedCombination(request));
    }
}
