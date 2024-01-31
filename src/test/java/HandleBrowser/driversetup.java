package HandleBrowser;

import java.time.Duration;
//import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class driversetup {
	public static WebDriver driver;


	public static WebDriver getWebDriver(String re) {
		// TODO Auto-generated method stub
		//WebDriver driver = new ChromeDriver();
		//System.out.println("Choose browser chrome or edge");
		
//		Scanner sc = new Scanner(System.in);
//		String browser = sc.nextLine();
		
		if(re.equalsIgnoreCase("chrome")){
		 driver = new ChromeDriver();
		}
		else if(re.equalsIgnoreCase("edge")) {
	          driver = new EdgeDriver();
		}
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String baseUrl = "http://cookbook.seleniumacademy.com/Config.html";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		return driver;

	}

}
