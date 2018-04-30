package GetInput;

public class ExcelReader {
	protected DataLoader loader;
	protected int[] columnsToRead;

	public ExcelReader(String path){
		loader = new DataLoader(path);
	}
	
	public String[] nextRecord(){
		return loader.getNextRecord(columnsToRead);
	}
}
