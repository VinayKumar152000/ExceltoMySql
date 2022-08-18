package com.example.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Invoice;
import com.example.demo.services.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService service;

	@GetMapping("/save")
	public String saveExcelData() {

		List<Invoice> excelDataAsList = service.getExcelDataAsList();
		int noOfRecords = service.saveExcelData(excelDataAsList);
		return "Data Saved Succesfully " + noOfRecords;
	}
}
