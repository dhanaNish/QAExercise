package tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matcher;


public class searchInVacancies {

	public WebDriver driver;
	public int befEnterValues;
	public boolean displayKeyword;
	public WebElement table;
	public String sentKey;
	


	@Given("^user is on Vacancies page$")
	public void vacanciesPageNav()
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
		
		//Navigate to Vacancies page
		driver.get("http:\\www.openbetcareers.com");
		driver.findElement(By.className("site-nav--menu__link")).click();
		
	}
	
	@When("user checks for the keywords input field")
	public void checkKeyword()
	{
		displayKeyword = driver.findElement(By.className("srSearchInput")).isDisplayed();
		
	}
	
	@Then("user can find and edit in the keywords input field")
	public void findKeyword()
	{
		String sendValue= "Engineer";
		driver.findElement(By.className("srSearchInput")).sendKeys(sendValue);
		Boolean aftResult=(displayKeyword && (driver.findElement(By.className("srSearchInput")).getAttribute("value").equals(sendValue))); 
		
		Assert.assertTrue("Verification failed: User is not able to find and edit in Keywords field", aftResult);
		driver.quit();
	}
	
	@When("user edits the keywords field and press enter")
	public void search()
	{
		sentKey="marketing";
		befEnterValues=driver.findElements(By.tagName("tr")).size();
		driver.findElement(By.className("srSearchInput")).sendKeys(sentKey + Keys.ENTER);
		
		
	}
	
	@Then("new results based on search are displayed in the Vacancies page")
	public void searchVac() throws InterruptedException
	{
		
		Thread.sleep(4000);
		
		List<WebElement> rows_table = driver.findElements(By.tagName("tr"));
		int rows_count=rows_table.size();
		System.out.println("Row count final : "+ rows_count);
		
		String searchedResult = rows_table.get(0).findElement(By.xpath("//td[1]")).getText();
		
		Boolean aftResult = ((befEnterValues!=rows_count)&& (searchedResult.toLowerCase().contains(sentKey.toLowerCase())));
		
		Assert.assertTrue("Verification failed: Search values are not populated according to the keywords provided", aftResult);
		driver.quit();
		
	}
	
	@When("user enter value in the keywords field")
	public void withoutEnter()
	{
		sentKey="lead";
		befEnterValues=driver.findElements(By.tagName("tr")).size();
		driver.findElement(By.className("srSearchInput")).sendKeys(sentKey);
			
	}
	
	@Then("search does not happen in the Vacancies page")
	public void noEnter() throws InterruptedException
	{
		Thread.sleep(4000);
		List<WebElement> rows_table = driver.findElements(By.tagName("tr"));
		int rows_count=rows_table.size();
		
		String searchedResult = rows_table.get(0).findElement(By.xpath("//td[1]")).getText();
		Boolean aftResult= ((befEnterValues==rows_count) && !(searchedResult.toLowerCase().contains(sentKey.toLowerCase())));
		Assert.assertTrue("Verification failed : Search happens in Vacancies page without pressing Enter key",aftResult);
		driver.quit();
	}
	
	@When("user enters special characters in the \"([^\"]*)\" field and press enter")
	public void enterSplChar(String keywords)
	{
		sentKey = keywords;
		driver.findElement(By.className("srSearchInput")).sendKeys(sentKey + Keys.ENTER);
		System.out.print(sentKey);
		System.out.println("user enters special characters in the keywords field and press enter");
	}
	
	@Then("keywords field ignores special character and populates results")
	public void ignoreSplChar() throws InterruptedException
	{
		String igrKey = sentKey.replaceAll("[^a-zA-Z0-9\\s+]", "");
		
		Thread.sleep(4000);
		List<WebElement> rows_table = driver.findElements(By.tagName("tr"));
		String searchedResult = rows_table.get(0).findElement(By.xpath("//td[1]")).getText();
		Boolean aftRes=searchedResult.toLowerCase().contains(igrKey.toLowerCase());
		
		
		Assert.assertTrue("Verification failed : Keywords field is not ignoring special characters",aftRes);
		driver.quit();
	}
}
