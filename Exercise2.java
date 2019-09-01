package exercise;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// Q. Implement a method named loginTest() for the following scenario:
// step 1 - You will navigate to some web page that has a 'Sign In' link, then click on it.
// step 2 - It opens a new tab to sign in, navigate to that new tab.
// step 3 - Enter the user name/password and hit next.
// step 4 - You will be signed in, verify that the profile image is displayed
public class Exercise2 {
	WebDriver driver;
	
	@BeforeMethod
	void setUpMethod() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/patri/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@Test
	void loginTest() {
		// step 1 - You will navigate to some web page that has a 'Sign In' link, then click on it.
		driver.get("https://artsandculture.google.com/");
		driver.findElement(By.linkText("Sign in")).click();
		
		// step 2 - It opens a new tab to sign in, navigate to that new tab.
		Set<String> tabs = driver.getWindowHandles();
		for(String tab : tabs) {
			driver.switchTo().window(tab);
			if(driver.getTitle().equals("Sign in - Google Accounts")) {
				break;
			}
		}
		// step 3 - Enter the user name/password and hit next.
		driver.findElement(By.id("identifierId")).sendKeys("YOUR EMAIL ADDRESS HERE");
		driver.findElement(By.id("identifierNext")).click();
		
		driver.findElement(By.name("password")).sendKeys("YOUR PASSWORD HERE");
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
		driver.findElement(By.id("passwordNext")).click();
		
		// step 4 - You will be signed in, verify that the profile image is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//img[@title='Profile']")).isDisplayed());
		
	}
}
