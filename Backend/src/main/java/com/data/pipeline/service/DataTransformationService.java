package com.data.pipeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.pipeline.entity.SalesData;
import com.data.pipeline.exception.ResourceNotFoundException;
import com.data.pipeline.reposiory.SalesDataRepository;

@Service
public class DataTransformationService {

	@Autowired
	SalesDataRepository repo;

	public Map<String, Integer> aggregateSalesByProduct() {
		List<SalesData> salesDataList= repo.findAll();
		if(salesDataList.isEmpty()) {
			throw new ResourceNotFoundException("Data not present in the Database");
		}
		else {
			Map<String, Integer> productSalesMap = new HashMap<>();
			for (SalesData salesData : salesDataList) {
				String productName = salesData.getProductName();
				int quantitySold = salesData.getQuantitySold();

				productSalesMap.put(productName, productSalesMap.getOrDefault(productName, 0) + quantitySold);
			}
			return productSalesMap;
		}
	}

	public Map<String, Integer> aggregateSalesByCategory() {

		List<SalesData> salesDataList = repo.findAll();
		if(salesDataList.isEmpty()) {
			throw new ResourceNotFoundException("Data not present in the Database");
		}else {
			Map<String, Integer> productSalesMap = new HashMap<>();
			for (SalesData salesData : salesDataList) {
				String categoryName = salesData.getCategory();
				int quantitySold = salesData.getQuantitySold();

				productSalesMap.put(categoryName, productSalesMap.getOrDefault(categoryName, 0) + quantitySold);
			}
			return productSalesMap;
		}
	}
}
