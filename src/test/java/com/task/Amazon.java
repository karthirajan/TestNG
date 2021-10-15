package com.task;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Amazon {
	
	static WebDriver driver;
	static long startTime;
	static String first;
	
	@BeforeClass(groups="smoke")
	public void beforeClass() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\karthi\\Demo\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.amazon.com/");
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
	
	@Test(groups = "smoke")
	public void search() throws Throwable {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles",Keys.ENTER);
		
		Thread.sleep(2000);

	}

}
