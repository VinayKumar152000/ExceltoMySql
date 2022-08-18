package com.example.demo.services.impls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.store.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Invoice;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repo;

	Workbook workbook;

	@Override
	public List<Invoice> getExcelDataAsList() {

		String excelPath = "D:\\Vinay\\ExcelToMySQL\\src\\main\\resources\\static\\ExcelFile.xlsx";
		List<String> list = new ArrayList<String>();

		// DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
			workbook = WorkbookFactory.create(new File(excelPath));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Getting number of columns in the Sheet
		int noOfColumns = sheet.getRow(0).getLastCellNum();

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<Invoice> invList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return invList;

	}

	private List<Invoice> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Invoice> invList = new ArrayList<>();
		int i = noOfColumns;
		do {
			Invoice inv = new Invoice();
			inv.setName(excelData.get(i));
			inv.setAmount(excelData.get(i + 1));
			inv.setNumber(excelData.get(i + 2));

			invList.add(inv);
			i = i + (noOfColumns);
		} while (i < excelData.size());

		return invList;
	}

	@Override
	public int saveExcelData(List<Invoice> invoices) {
		invoices = repo.saveAll(invoices);
		return invoices.size();
	}

}
