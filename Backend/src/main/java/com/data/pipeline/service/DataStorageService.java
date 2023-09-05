package com.data.pipeline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.pipeline.entity.SalesData;
import com.data.pipeline.exception.ResourceNotFoundException;
import com.data.pipeline.reposiory.SalesDataRepository;

@Service
public class DataStorageService {
    @Autowired
    private SalesDataRepository salesDataRepository;

    public void storeData(List<SalesData> data) {
    	System.out.println("list of data ");
    	
    	for(SalesData o: data) {
    		System.out.println(o);
    	}
    	
    	List<SalesData> salesDataList =  salesDataRepository.saveAll(data);
    	
    	if(salesDataList.isEmpty()) {
    		throw new ResourceNotFoundException("Sales data not present in the DB");
    	}
    }
    
    
}

