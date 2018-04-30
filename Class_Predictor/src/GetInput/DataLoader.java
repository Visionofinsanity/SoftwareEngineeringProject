package GetInput;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.*;
import com.monitorjbl.xlsx.StreamingReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class DataLoader {
	public String[] headers;
	private InputStream is;
	private Workbook excelWorkbook;
	private Iterator<Row> rowIterator;
	
	public DataLoader(String path){
		getRowIterator(path, excelWorkbook);
		if(rowIterator.hasNext()) {
			Row titles = rowIterator.next();
			headers = new String[titles.getLastCellNum()];
			Iterator<Cell> cellIterator = titles.iterator();
			
			int index = 0;
			while(cellIterator.hasNext()) {
				Cell title = cellIterator.next();
				headers[index] = title.getStringCellValue();
				index++;
			}
		}
		
	}
	
	public void getRowIterator(String path, Workbook book){
		try{
			is = new FileInputStream(new File(path));
			book = StreamingReader.builder()
					.rowCacheSize(100)
					.bufferSize(4096)
					.open(is);
			rowIterator = book.getSheetAt(0).iterator();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public String[] getNextRecord(int[] columns){
		if(rowIterator.hasNext()){
			Row currentRow = rowIterator.next();
			String[] record = new String[columns.length];
			Iterator<Cell> cellIterator = currentRow.iterator();
			int column = 1;
			int nextColumn = 0;
			
			
			while (cellIterator.hasNext()){
				Cell currentCell = cellIterator.next();
				if(column == columns[nextColumn]){
					if(currentCell.getCellTypeEnum() == CellType.STRING)
						record[nextColumn] = currentCell.getStringCellValue();
					else if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
						record[nextColumn] = "" + currentCell.getNumericCellValue();
					else
						record[nextColumn] = "Invalid Data Type";
					if(nextColumn == columns.length - 1)
							return record;
					else
						nextColumn++;
				}
				column++;
			}
			
			return record;
		}else {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
