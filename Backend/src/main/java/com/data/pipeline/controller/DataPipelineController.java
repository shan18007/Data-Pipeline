package com.data.pipeline.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.data.pipeline.response.APIResponse;
import com.data.pipeline.service.DataPipelineService;
import com.data.pipeline.service.DataTransformationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/pipeline")
@CrossOrigin(origins = "http://localhost:4200/")
public class DataPipelineController {
	
	 
	 @Autowired
	 private DataPipelineService dataPipelineService;

	 @Autowired
	 DataTransformationService dataTransformationService;

	@PostMapping("/upload")
    public ResponseEntity<APIResponse> uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
		
       return new ResponseEntity<>(dataPipelineService.storeDataInDB(file),HttpStatus.OK);

    }
	
	@GetMapping("/product")
    public ResponseEntity<APIResponse> analyseByProduct() throws IOException {
		
		Map<String, Integer> data = dataTransformationService.aggregateSalesByProduct();
		
       return new ResponseEntity<>(dataPipelineService.setSuccessResponse(data),HttpStatus.OK);

    }
	
	@GetMapping("/category")
    public ResponseEntity<APIResponse> analyseByCategory() throws IOException {
		
		Map<String, Integer> data = dataTransformationService.aggregateSalesByCategory();
		
       return new ResponseEntity<>(dataPipelineService.setSuccessResponse(data),HttpStatus.OK);

    }
}
