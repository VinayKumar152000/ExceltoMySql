package com.example.demo.services;
import java.util.*;

import com.example.demo.domain.Invoice;

public interface InvoiceService {

    List<Invoice> getExcelDataAsList();
	
	int saveExcelData(List<Invoice> invoices);
}
