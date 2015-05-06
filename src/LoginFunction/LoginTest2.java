package LoginFunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest2 {

	
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader("C:\\workspace fauzia\\LoginFunctionality\\Login.xlsx");

	int rows=xls.getRowCount("+ve_scenario");
	int cols=xls.getColumnCount("+ve_scenario");
	
    
    
    
@BeforeTest
public void start(){
		driver=new FirefoxDriver();
}
	
@Test(dataProvider="getdata")
public void dologin(String username, String password, String results, String actresult ){
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://www.inbox.com");
	driver.findElement(By.xpath("//*[@id='txtEmail']")).sendKeys(username);
	driver.findElement(By.xpath("//*[@id='txtPass']")).sendKeys(password);
	driver.findElement(By.xpath("//div[@class='login']/form[1]/div/div[5]/button")).sendKeys(Keys.ENTER);
	String y=driver.getCurrentUrl();
	System.out.println(y);
	
	//login validation
	String expected_url="http://www.inbox.com/default.aspx?tbid=70001";
	 
	if(y.contentEquals(expected_url))
	{
		actresult="login successful";
		System.out.println("Login passed and test passed");
		
		
		
	}
	
	else{
		actresult="login failed";
		System.out.println("test failed");
	}
	
//putting results in xls file
	for(int newrow=2;newrow<=rows;newrow++){
xls.setCellData("+ve_scenario", "result", newrow, actresult);
}
	
//to sign out
	driver.findElement(By.xpath("//div[@id='top']/a[4]")).sendKeys(Keys.ENTER);
	}

	@AfterTest
	public void close(){
		driver.close();
	}

	
	
	
	

@DataProvider
public Object[][] getdata(){

	
	//starting from specific row
	Object [][] data=new Object[rows-1][cols];
	   for(int rownum=2;rownum<=rows;rownum++){
		        for(int colnum=0; colnum<cols;colnum++){
		        	
     //getting each cell data
		        	data[rownum-2][colnum]=xls.getCellData("+ve_scenario", colnum, rownum);
		        	
		        	
		        }
		    
		
	}
	
	return data;
}

}
