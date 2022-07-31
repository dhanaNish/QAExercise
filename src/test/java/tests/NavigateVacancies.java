package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class NavigateVacancies {
	public WebDriver driver;
	public WebElement header;


	@Given("^user is on homepage$")
	public void homepage() throws IOException
	{
		//Chrome driver implementation
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KoKo\\Desktop\\Dhana\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Close new tab
		String currHandle = driver.getWindowHandle();
		String winHandle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(winHandle);
        driver.close();
		driver.switchTo().window(currHandle);
		
		//Navigate to homepage
		driver.get("http:\\www.openbetcareers.com");
		//System.out.println("User is on homepage");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
							
	}
	
	@When("user checks for Vacancies menu in top of homepage")
	public void checkVacancies()
	{
		header = driver.findElement(By.xpath("/html/body/div/header"));
		
		
	}
	
	@Then("Vacancies menu is present in homepage header section")
	public void vacanciesMenu()
	{
		
		String s = header.findElement(By.className("site-nav--menu__link")).getText();
		
		Assert.assertEquals("Verification failed: Vacancies menu has a different name","Vacancies", s);
		driver.quit();
	}
	
	@When("user clicks on Vacancies menu")
	public void clickVacancies()
	{
		driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li[1]/a")).click();
				
	}
	
	@Then("user is navigated to Open vacancies page")
	public void navVacancies()
	{
		String vacanciesUrl= "https://www.openbetcareers.com/#vacancies";
		String currUrl= driver.getCurrentUrl();
		Assert.assertEquals("Verification failed : User is not navigated to vacancies page",currUrl, vacanciesUrl);
		driver.quit();
	}
}
