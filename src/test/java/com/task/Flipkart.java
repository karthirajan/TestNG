package com.task;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Flipkart {
	
	
	@DataProvider(name="mobileName")
	public Object[][] data() {
		return new Object[][] {{"reamle c21y black"}};
		

	}
	
	@DataProvider(name="username")
	public Object[][] user() {
		return new Object[][] {{"vijay1995"}};
		

	}
	
	@DataProvider(name="loop")
	public Object[][] pass() {
		return new Object[][] {{4656545},{4565465},{64657645}};
		

	}
	
	static WebDriver driver;
	static long startTime;
	static String first;
	
	@BeforeClass(groups="smoke")
	public void beforeClass() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\karthi\\Demo\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);

	}
	
	@BeforeMethod(groups="smoke")
	public void beforeMethod() {
		
		 startTime = System.currentTimeMillis();

	}
	
	@AfterMethod(groups="smoke")
    public void afterMethod() {
		
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}
	
	@AfterClass(groups="smoke")
    public static void afterClass() {
		
		driver.quit();

	}
	
	/*@Test(priority = -1,dataProvider = "username",groups = "smoke")
	public void login(String usr) throws Throwable {
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[2]")).sendKeys("9597666445");
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[3]")).sendKeys(usr);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(2000);

	}*/
	
	@Parameters({"username","password"})
	@Test(priority = -1,groups = "smoke")
	public void login(String username, String password) throws Throwable {
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[2]")).sendKeys(username);
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[3]")).sendKeys(password);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(2000);

	}
	
	@Test(priority = 1,dataProvider = "mobileName",groups="smoke")
	public void search(String value) throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(value);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		Thread.sleep(2000);

	}
	
	@Test(priority = 2,groups="smoke")
	public void getText() throws InterruptedException {
		
		WebElement text = driver.findElement(By.xpath("(//*[@class='_4rR01T'])[1]"));
	    first = text.getText();
		System.out.println(first);
		Thread.sleep(3000);
		text.click();
		Thread.sleep(3000);

	}
	
	public void windowHandle() throws InterruptedException {
		Thread.sleep(5000);
		String par = driver.getWindowHandle();
		Thread.sleep(3000);
		Set<String> child = driver.getWindowHandles();
		
		for(String x : child) {
			if(!par.equals(x)) {
				System.out.println("tab switched");
				driver.switchTo().window(x);
			}
		}

	}
	
	@Test(priority = 3,groups="smoke")
	public void windows() throws InterruptedException {
		
		windowHandle();
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement real = driver.findElement(By.xpath("//*[@class='B_NuCI']"));
		wait.until(ExpectedConditions.elementToBeClickable(real));
		String second = real.getText();
		System.out.println(second);
		
		if(first == second) {
			System.out.println("The Parent Window Name and Child Window Name are SAME");
		}
		else {
			System.out.println("The Parent and child Window name are NOT SAME");
		}
		WebElement down = driver.findElement(By.xpath("//*[text()='RMX3261 / RMX3263']"));
		JavascriptExecutor jsc = (JavascriptExecutor)driver;
		jsc.executeScript("arguments[0].scrollIntoView(true)",down);
		Thread.sleep(2000);
		
		
		WebElement mark = driver.findElement(By.xpath("//*[text()='Smartphones']"));
		//Assert.assertTrue(mark.isSelected());
		System.out.println(mark.getText());
		String text = mark.getText();
		System.out.println(text);
		Assert.assertEquals("Smartphones", text);
		
		Thread.sleep(3000);
		Actions ac = new Actions(driver);
		Thread.sleep(3000);
		//ac.moveToElement(mark).perform();
		ac.doubleClick(mark).build().perform();

	}
	
	
/*	@Test(priority = 4,enabled = false)
	public void screenshot() {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\aruna\\eclipse-workspace\\ProjFlipkt\\screen\\shot");

	}
	
	@Test(dataProvider = "loop",groups = "skip") //to execute a method multiple times
	public void loop(int a) {
		
           System.out.println("loop " + a);		

	}
	
	
	@Test(invocationCount = 10,groups = "skip") //to execute a method multiple times
	public void loop() {
		
           System.out.println("loop");		

	}*/
}
