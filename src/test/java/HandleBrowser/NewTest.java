package HandleBrowser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class NewTest {
	
	public static	WebDriver driver;
  @Test(priority=0)
  @Parameters("browser")
	public static void createDriver(String bre) {
		driver = driversetup.getWebDriver(bre);
	}
  @Test(priority=1)
  private static void configure() throws IOException {
		//excel sheet
		String file="C:\\Users\\2303831\\eclipse-workspace\\Test\\src\\test\\resources\\Project.xlsx";
		String Make=CommonUtils.getCellData(file, "Sheet1", 1, 0);	
		String Fueltype = CommonUtils.getCellData(file, "Sheet1", 1, 1);
		String Features = CommonUtils.getCellData(file, "Sheet1", 1, 2);
		String Colour = CommonUtils.getCellData(file, "Sheet1", 1, 3);
				
		//selecting car make
		Select sc = new Select(driver.findElement(By.name("make")));
		sc.selectByVisibleText(Make);
		
		
		//selecting fuel type		
		List<WebElement> option = driver.findElements(By.xpath("//*[@id=\"tabs-1\"]/p[2]/input"));
		for(WebElement i : option) {
			if(i.getAttribute("value").equals(Fueltype)) {
				i.click();
				break;
			}
		}
		
		
		
//selecting optional features	
		
		String name = Features;
		name = name.replace(" ", "");
		driver.findElement(By.xpath("//input[@value='"+name+"']")).click();
				

//selecting car color
		Select scm = new Select(driver.findElement(By.name("color")));	
		scm.selectByVisibleText(Colour);
		
//clicking help 
		driver.findElement(By.xpath("//*[@id='helpbutton']")).click();
//clicking online chat 
		driver.findElement(By.id("chatbutton")).click();
//clicking visit us
		driver.findElement(By.id("visitbutton")).click();

	}

  @Test(priority=2)
	public static void handleWindows() {
		Set<String> a = new HashSet<String>();
		a = driver.getWindowHandles();
		
		//number of browser windows
		System.out.println("Number of browser windows opened: "+a.size());
		//collection of browser window id's
		List<String> b = new ArrayList<String>(a);
		System.out.println("Collection of windows Ids : " +b);
		
		//finding visit us is there or not
		for(String windowId: b) {
			String title=driver.switchTo().window(windowId).getTitle();
			if(title.equals("Visit Us")) {
				System.out.println("Visit us Title Found");
				driver.close();
				break;
			}
		}
				
		//navigating back to main browser window
		String parentwindow = b.get(0);
		driver.switchTo().window(parentwindow);
		
		
		//closing all windows
		driver.quit();
		
		}
	
}
