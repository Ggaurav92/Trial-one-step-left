package com.Amazon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestInMethod {
	
	
	
	
	private static String output(XSSFSheet sheet, int rownr, int colnr) {
        /*
         * This method displays the total value of 
         */
		//assigning rownr from findRow to row
        XSSFRow row = sheet.getRow(rownr);
        //getting the cell content by using row from above and colnr parameter in GetStepName as j
        XSSFCell cell = row.getCell(colnr);
        String cellOut = ""+cell;    
                return cellOut;
                
    }//end of output

	private static int findRow(XSSFSheet sheet, String cellContent) {
		//for loop to search each row in sheet
        for (Row row : sheet) {
        	//for loop to search each cell in the above referenced row
            for (Cell cell : row) {
            	//it will only look for celltype String
                if (cell.getCellType() == CellType.STRING) {
                	//This will take string from cell trim it and then compare to our given cellContent
                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                        return row.getRowNum();  
                    }
                }
            }
        }               
        return 0;
    }


    public static void finish() {

        System.exit(0);
    }

	public String GetStepName(String fileName, String cellContent,int i,int j) throws IOException {
        int rownr=0, colnr = j;
        //Opening the excel sheet at path fileName
        InputStream input = new FileInputStream(fileName);
        // Using XSSFWorkbook to read the Excel Sheet
        XSSFWorkbook wb = new XSSFWorkbook(input);
        //taking the first sheet(0) in the excel sheet 
        XSSFSheet sheet = wb.getSheetAt(0);

        // this will find the row which has the Classname (in our Case Test Case Name)
        rownr = findRow(sheet, cellContent);
        //adding the iterator i from aftermethod
        rownr = rownr+i;
        //column index starts from 0 hence when you give col 8 it takes 7. hence subtracting 1
        colnr = colnr-1;
        //this will search for the referenced cell with reference to cell that has Class name
        String giveBack = output(sheet, rownr, colnr);
        
        //finish();
        return giveBack;
	}

}
