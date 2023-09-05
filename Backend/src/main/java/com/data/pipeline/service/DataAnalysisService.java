package com.data.pipeline.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.pipeline.entity.SalesData;

@Service
public class DataAnalysisService {
    
	@Autowired
    private DataTransformationService dataTransformationService;

    public void analyzeSalesData(List<SalesData> salesDataList) {
        Map<String, Integer> productSalesMap = dataTransformationService.aggregateSalesByProduct();
        
        for (Map.Entry<String, Integer> entry : productSalesMap.entrySet()) {
            System.out.println("Product: " + entry.getKey() + ", Total Sales: " + entry.getValue());
        }
    }
    
    public void analyzeSalesDataByCategory(List<SalesData> salesDataList) {
        Map<String, Integer> productSalesMap = dataTransformationService.aggregateSalesByCategory();
        
        for (Map.Entry<String, Integer> entry : productSalesMap.entrySet()) {
            System.out.println("Product: " + entry.getKey() + ", Total Sales: " + entry.getValue());
        }
    }
}

