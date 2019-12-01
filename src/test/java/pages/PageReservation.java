package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageReservation 
{
	private WebDriver driver;
	private By passangerDrop;
	private By fromPortDrop;
	private By toPortDrop;
	
	public PageReservation(WebDriver webdriver) {
		this.driver = webdriver;
		passangerDrop = By.name("passCount");
		fromPortDrop = By.name("fromPort");
		toPortDrop = By.name("toPort");
	}


	public void selectPassengers(int cant)
	{
		WebElement element = waitWebElement(driver, passangerDrop, 10);
		
		//Models a SELECT tag, providing helper methods to select and deselect options.
		Select selectPasajeros = new Select(element);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	
	public void selectFromPort(int index)
	{
		WebElement element = waitWebElement(driver, fromPortDrop, 10);
		
		Select selectFromPort = new Select(element);
		selectFromPort.selectByIndex(index);
	}
	
	public void selectToPort(String city)
	{
		WebElement element = waitWebElement(driver, toPortDrop, 10);
		
		Select selectToPort = new Select(element);
		selectToPort.selectByValue(city);
	}
	
	public WebElement waitWebElement(WebDriver webdriver, By locator, int Duracion)
	{
		//tiempo de espera del elemento
		Duration timeout = Duration.ofSeconds(Duracion);
		//Explicit Wait is code you define to wait for a certain condition to occur before proceeding further in the code.
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);
		//Selenium encapsulates every form element as an object of WebElement.
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		return element;
	}
}
