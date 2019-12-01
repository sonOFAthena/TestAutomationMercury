package pages;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;

public class PageLogin {

	private WebDriver webdriver;
	public By userField;
	public By passwordField;
	public By loginButton;
	public By fields;

	public PageLogin(WebDriver driver) 
	{
		this.webdriver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("login");
		fields = By.tagName("input");
	}
	
	public void login(String username, String password)
	{
		this.setUserField(username);
		this.setPasswordField(password);
		this.clickLogin();
		
		// Generar un screenshot
		File myScreenShot = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		
		Date fecha = new Date();
		DateFormat formato = new SimpleDateFormat("yyyy-mm-dd HH.mm.ss");
		String path = "Evidencia\\LOGIN " + formato.format(fecha) + ".png";
		File file = new File(path);
		
		try {
			FileUtils.copyFile(myScreenShot, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*Click on login button*/
    public void clickLogin()
    {
        webdriver.findElement(loginButton).click();
    }    
    
    //Set user name in textbox
	public void setUserField(String userName) {
		webdriver.findElement(userField).sendKeys(userName);
	}
	
	//Set password in password textbox
	public void setPasswordField(String password) {
		webdriver.findElement(passwordField).sendKeys(password);
	}
	
	/**
	 * Llena el campo user y pass usando una lista de webelement que usan el tag "input"
	 * @param user
	 * @param pass
	 */
	public void fields_login(String user, String pass)
	{
		List<WebElement> loginFields = webdriver.findElements(fields);
		loginFields.get(2).sendKeys(user);
		loginFields.get(3).sendKeys(pass);
		loginFields.get(4).click();
	}
	
}
