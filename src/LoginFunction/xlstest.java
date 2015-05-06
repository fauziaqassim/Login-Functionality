package LoginFunction;

public class xlstest {

	public static void main(String[] args) {
		
		Xls_Reader xls=new Xls_Reader("C:\\workspace fauzia\\LoginFunctionality\\Login.xlsx");

		int rows=xls.getRowCount("-ve_scenario");
		int cols=xls.getColumnCount("-ve_scenario");
		// TODO Auto-generated method stub
		for(int newrow=2;newrow<=rows;newrow++){
xls.setCellData("-ve_scenario", "result", newrow, "data");}
	}

}
