import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BotMain {
	static String loginID = null;
	static String loginPW = null;
	static String loginUrl = null;
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = setAppProperties(args[0]);
		driver.get(loginUrl);
		driver.findElement(By.id("email")).sendKeys(loginID);
		driver.findElement(By.id("password")).sendKeys(loginPW);
		driver.findElement(By.id("submit-button"));
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a.-link.js-site-switcher-button.js-gps-track")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("log out")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.grid--cell.s-btn.s-btn__primary")).click();
		driver.quit();
	}

	private static WebDriver setAppProperties(String cfgfile) throws IOException {
		InputStream is = new FileInputStream(cfgfile);
		Properties p = new Properties();
		p.load(is);
		is.close();
		loginID = p.getProperty("Email");
		loginPW = p.getProperty("Password");
		loginUrl = p.getProperty("LoginUrl");
		String os = System.getProperty("os.name");
		ChromeOptions coptions = new ChromeOptions();  
		coptions.setHeadless(true);
		FirefoxOptions foptions = new FirefoxOptions();  
		foptions.setHeadless(true);
		if (os.startsWith("Windows")) {
			if (!p.getProperty("ChromeWindows").equals("")) {	
				System.setProperty("webdriver.chrome.driver", p.getProperty("ChromeWindows"));
				return new ChromeDriver(coptions);
			}
			System.setProperty("webdriver.gecko.driver", p.getProperty("FireFoxWindows"));
			return new FirefoxDriver(foptions);
		} else {
			if (!p.getProperty("ChromeLinux").equals("")) {
				
				System.setProperty("webdriver.chrome.driver", p.getProperty("ChromeLinux"));
				return new ChromeDriver(coptions);
			}
			System.setProperty("webdriver.gecko.driver", p.getProperty("FireFoxLinux"));
			return new FirefoxDriver(foptions);
		}
	}

}
