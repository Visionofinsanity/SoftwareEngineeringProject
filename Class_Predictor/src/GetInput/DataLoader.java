package GetInput;

import org.apache.poi.ss.usermodel.*;
import com.monitorjbl.xlsx.StreamingReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Lower level handling of the Excel reader, using iterators and a Streaming
 * Excel sheet.
 * @author Brennan Gorman, Caleb Sapp
 *
 */
public class DataLoader {
	public String[] headers;
	private InputStream is;
	private Workbook excelWorkbook;
	private Iterator<Row> rowIterator;
	
	/**
	 * Sets up class to start reading from file denoted from file path. Also
	 * saves the first row as headers.
	 * @param path
	 */
	public DataLoader(String path){
		//initialize row iterator
		getRowIterator(path, excelWorkbook);
		
		//if document not empty
		if(rowIterator.hasNext()) {
			//Set up for iterating over each cell
			Row titles = rowIterator.next();
			headers = new String[titles.getLastCellNum()];
			Iterator<Cell> cellIterator = titles.iterator();
			
			//convert title row into String array for headers
			int index = 0;
			while(cellIterator.hasNext()) {
				Cell title = cellIterator.next();
				headers[index] = title.getStringCellValue();
				index++;
			}
		}
		
	}
	
	/**
	 * Links the row iterator to the streaming reader so that the program
	 * only reads in a max of 100 records at a time to prevent memory overload.
	 * @param path
	 * @param book
	 */
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
	
	/**
	 * Takes the record, and creates a new condensed one based off the columns
	 * that will be taken.  Column numbers start at 1 for the first column.
	 * Returns the condensed row
	 * @param columns
	 * @return
	 */
	public String[] getNextRecord(int[] columns){
		if(rowIterator.hasNext()){
			Row currentRow = rowIterator.next();
			String[] record = new String[columns.length];
			Iterator<Cell> cellIterator = currentRow.iterator();
			int column = 1;
			int nextColumn = 0;
			
			//While there is another cell
			while (cellIterator.hasNext()){
				Cell currentCell = cellIterator.next();
				//if the cell is one we want to pull information from
				if(column == columns[nextColumn]){
					//gets string from string cell
					if(currentCell.getCellTypeEnum() == CellType.STRING)
						record[nextColumn] = currentCell.getStringCellValue();
					//converts number cell to string
					else if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
						record[nextColumn] = "" + currentCell.getNumericCellValue();
					//registers blak cell as -
					else if (currentCell.getCellTypeEnum() == CellType.BLANK) {
						record[nextColumn] = "-";
					//All other cells are Invalid Data Types
					}else
						record[nextColumn] = "Invalid Data Type";
					//if this was last column need, return condensed record
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
				e.printStackTrace();
			}
			return null;
		}
	}
}
