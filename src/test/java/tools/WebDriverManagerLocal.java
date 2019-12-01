package tools;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;


public class WebDriverManagerLocal {
	
	public static void setWindowsSize(WebDriver driver, String size) {
		if(size.equalsIgnoreCase("maximazed"))
		{
			driver.manage().window().maximize();
		}
		if(size.equalsIgnoreCase("fullscreen"))
		{
			driver.manage().window().fullscreen();
		}
	}
	
	public static void setWindowsSize(WebDriver driver, int x, int y) {
		driver.manage().window().setSize(new Dimension(x,y));
	}
	
	public static void setWindowsPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(x,y));
	}
}
