package LoginFunction;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//checking negative scenario for login functionality
public class LoginTest {
	
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader("C:\\workspace fauzia\\LoginFunctionality\\Login.xlsx");

	int rows=xls.getRowCount("-ve_scenario");
	int cols=xls.getColumnCount("-ve_scenario");
	
    //String expected_url;
    
    
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
	//String x=driver.findElement(By.xpath("//div[@class='login']/form[1]/div/div[5]/button")).getText();
	driver.findElement(By.xpath("//div[@class='login']/form[1]/div/div[5]/button")).sendKeys(Keys.ENTER);
	String y=driver.getCurrentUrl();
	System.out.println(y);
	
	//validation
	String expected_url="https://www.inbox.com/login.aspx";
	 
	if(y.contentEquals(expected_url))
	{
		actresult="fail";
		System.out.println("Login failed and test passed");
		
		
		
	}
	
	else{
		actresult="pass";
		System.out.println("test failed");
	}
	
	//putting results in xls file
	for(int newrow=2;newrow<=rows;newrow++){
xls.setCellData("-ve_scenario", "result", newrow, actresult);
}
	}
//closing browser
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
		        	data[rownum-2][colnum]=xls.getCellData("-ve_scenario", colnum, rownum);
		        	
		        	
		        }
		    
		
	}
	
	return data;
}

}
