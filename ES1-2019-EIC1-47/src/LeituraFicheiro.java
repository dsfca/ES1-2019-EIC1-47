import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeituraFicheiro extends JPanel{
	JTable jt;


	// construtor
	public LeituraFicheiro() {
		
	}

	//metodo para criar um fileChooser(que nos da opcao para escolher o ficheiro)
	public File openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}else {
			return null;
		}
	}

	//Metodo para ler o Ficheiro Excel

	public void CorreFicheiro() throws IOException{
		int count = 0;
		// Columns for table
		String[] columns = new String[12];
		// 2D array is used for data in table
		String[][] linhas = new String[421][];

		FileInputStream fis= new FileInputStream(openFile());
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(0);
		File file = new File("Method.txt");

		FormulaEvaluator forlulaval = wb.getCreationHelper().createFormulaEvaluator();
		//Cell c=new Cell();
		int countLinhas = 0;
		for (Row row : sheet) {

			//System.out.println(row)
			count = 0;
			String[] linha = new String[16];
			for (Cell cell : row) {

				// pega celulas de cada linhas
				switch(forlulaval.evaluateInCell(cell).getCellType()){

				case NUMERIC:
					if(countLinhas == 0) {
						columns[count] = Double.toString(cell.getNumericCellValue());
					}else {
						linha[count] = Double.toString(cell.getNumericCellValue());
					}
					//System.out.print(cell.getNumericCellValue()+"\t\t");
					count++;
					break;
				case STRING:
					if(countLinhas == 0) {
						columns[count] =  cell.getStringCellValue();
					}else {
						linha[count] = cell.getStringCellValue();
						//System.out.print(cell.getStringCellValue()+"\t\t");
					}
					count++;
					break;
				case BOOLEAN:
					if(countLinhas == 0) {
						columns[count] = Boolean.toString(cell.getBooleanCellValue());
					}else {
						linha[count]= Boolean.toString(cell.getBooleanCellValue());
						//System.out.print(cell.getBooleanCellValue()+"\t\t");
					}
					count++;
					break;
				case BLANK:
					count++;
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;

				}
				linhas[countLinhas] = linha;

			}
			for(int i = 0; i < linha.length; i++) {
				//System.out.println(linha[i]);
			}
			countLinhas++;

		}
		jt = new JTable(linhas, columns) {
			// Determines if data can be entered by users
			public boolean isCellEditable(int data, int columns){
				return false;
			}


		};
		// Creates Table

		jt.setRowHeight(20);
		for(int i = 0; i < 12; i++) {
			jt.getColumnModel().getColumn(i).setWidth(100);;
		}
		// Set size of table     
		jt.setPreferredScrollableViewportSize(new Dimension(1000, 674));

		// This will resize the height of the table automatically 
		// to all data without scrolling. 
		jt.setFillsViewportHeight(true);

		JScrollPane jps = new JScrollPane(jt);
		add(jps);
	}


	// Creates Window
	
}
