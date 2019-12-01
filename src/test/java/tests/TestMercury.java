package tests;

import java.io.File;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.PageLogin;
import pages.PageReservation;
import tools.ScreenShooter;
import tools.WebDriverManagerLocal;

public class TestMercury 
{
	private WebDriver driver;
	private PageLogin pageLogin;
	private PageReservation pageReservation;
	
	private ArrayList<String> tabs;
	
	@BeforeMethod
	public void setUp() 
	{
		/*configure driver*/
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		// otros tamaños de pantalla
		/*
		WebDriverManagerLocal.setWindowsSize(driver, "fullscreen");
		WebDriverManagerLocal.setWindowsSize(driver, 400, 400);
		*/
		
		//cambiar la posicion del browser
		/*
		WebDriverManagerLocal.setWindowsPosition(driver, 200,200);
		*/
		driver.navigate().to("http://newtours.demoaut.com");
		
		//abrir otros tabs
		JavascriptExecutor javascriptExec = (JavascriptExecutor)driver;
		String google = "window.open('https://www.google.com')";
		javascriptExec.executeScript(google);
		
		
		//All elements wait up to x seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		
		tabs = new ArrayList<String>(driver.getWindowHandles());
	}
	
	@Test
	public void loginIncorrecto()
	{
		WebDriverManagerLocal.setWindowsSize(driver, "maximazed");
		driver.switchTo().window(tabs.get(1)).navigate().to("https://www.youtube.com/channel/UCuJMVDLd_nWN2O5dfjVtKqA");
		driver.switchTo().window(tabs.get(0));
		pageLogin = new PageLogin(driver);
		pageLogin.login("user", "user");
		
		By xpath = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b");
		Assert.assertTrue(driver.findElement(xpath).getText().contains("Welcome back to"));
		
	}
	
	@Test
	public void login()
	{
		WebDriverManagerLocal.setWindowsSize(driver, "fullscreen");
		pageLogin = new PageLogin(driver);
		pageLogin.login("mercury", "mercury");
		
		By xpath = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		Assert.assertTrue(driver.findElement(xpath).getText().contains("Flight Finder to search"));
		
	}
	
	@Test
	public void fillForm()
	{
		WebDriverManagerLocal.setWindowsSize(driver, 400, 400);
		pageReservation = new PageReservation(driver);
		pageLogin = new PageLogin(driver);
		
		pageLogin.login("mercury", "mercury");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		pageReservation.selectPassengers(2);
		pageReservation.selectFromPort(3);
		pageReservation.selectToPort("London");	
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void AlterLogin()
	{
		pageLogin = new PageLogin(driver);
		pageLogin.fields_login("mercury", "mercury");
		
		By xpath = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		Assert.assertTrue(driver.findElement(xpath).getText().contains("Flight Finder to search"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(!result.isSuccess())
		{
			ScreenShooter.takeScreenshot("error", driver);
		}
		
		//webdriver.close();
		driver.switchTo().window(tabs.get(0)).close();
		System.out.println(tabs.size());
		
		if(tabs.size() == 2)
			driver.switchTo().window(tabs.get(1)).close();
	}
	
}
