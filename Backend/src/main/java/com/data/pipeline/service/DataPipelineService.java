package com.data.pipeline.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.data.pipeline.entity.SalesData;
import com.data.pipeline.response.APIResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataPipelineService {
	
	@Autowired
	DataStorageService dataStorageService;
	
	 public APIResponse storeDataInDB(MultipartFile file) {
		 log.info("Post called....");
	        if (!file.isEmpty()) {
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	                String line;
	                List<SalesData> salesDataList = new ArrayList<>();
	                
	                int i = 0 ;
	                while ((line = reader.readLine()) != null) {
	                	if(i == 0 ) {
	                		i++;
	                	}else {

	                    String[] data = line.split(",");

	                    if (data.length == 6) {
	                    	SalesData salesData = new SalesData();
	                        salesData.setProductID(data[0]);
	                        salesData.setProductName(data[1]);
	                        salesData.setPrice(Double.parseDouble(data[2]));
	                        salesData.setQuantitySold(Integer.parseInt(data[3]));
	                        salesData.setDate(LocalDate.parse(data[4]));
	                        salesData.setCategory(data[5]);
	                        salesDataList.add(salesData);
	                        
	                        log.info(salesData +"");
	                        
	                    }
	                	}
	                }
	                log.info(salesDataList + "");
			       dataStorageService.storeData(salesDataList);

	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	       return this.setSuccessResponse("Data Inserted Suceesfully in the table");
	        

	 }

     public APIResponse setSuccessResponse(Object data) {
    	
    	APIResponse response = new APIResponse();
    	response.setStatus(200);
    	response.setMessage("Success");
    	response.setData(data);
    	return response;
    }
}
