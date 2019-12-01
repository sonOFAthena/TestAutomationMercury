package tools;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShooter {
	
	public static void takeScreenshot(String screenName, WebDriver driver)
	{
		// Generar un screenshot
		File myScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Date fecha = new Date();
		DateFormat formato = new SimpleDateFormat("yyyy-mm-dd HH.mm.ss");
		String path = "Evidencia\\error " + formato.format(fecha) + ".png";
		File file = new File(screenName + "_" + path);
		
		try {
			FileUtils.copyFile(myScreenShot, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

