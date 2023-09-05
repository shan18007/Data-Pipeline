package com.data.pipeline.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.data.pipeline.entity.SalesData;

@Service
public class DataIngestionService {
	
	private Resource csvResource;

	public List<SalesData> ingestData() throws IOException {
		try (Reader reader = new InputStreamReader(csvResource.getInputStream());
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			List<SalesData> salesDataList = new ArrayList<>();
			for (CSVRecord record : csvParser) {
				SalesData salesData = new SalesData();
				salesData.setProductID(record.get("ProductID"));
				salesData.setProductName(record.get("ProductName"));
				salesData.setPrice(Double.parseDouble(record.get("Price")));
				salesData.setQuantitySold(Integer.parseInt(record.get("QuantitySold")));
				salesData.setDate(LocalDate.parse(record.get("Date")));
				salesData.setCategory(record.get("Category"));
				salesDataList.add(salesData);
			}
			return salesDataList;
		}
	}
}
