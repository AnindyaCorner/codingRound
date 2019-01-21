package testPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class FlightBookingTest {

	WebDriver driver;

	@Test

	public void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();
		
		driver = new ChromeDriver();

		driver.get("https://www.cleartrip.com/");

		waitFor(2000);

		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();

		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin

		waitFor(5000);

		//List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		
		List<WebElement> originOptions = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));

		originOptions.get(0).click();
		
		driver.findElement(By.id("ToTag")).clear();

		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		waitFor(5000);

		// select the first item from the destination auto complete list

		//List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		
		List<WebElement> destinationOptions = driver.findElements(By.xpath("//ul[@id='ui-id-2']/li"));

		destinationOptions.get(0).click();
		
		driver.findElement(By.id("DepartDate")).click();

		driver.findElement(By.xpath("//span[.='February']/../../..//a[.='25']")).click();

		// all fields filled in. Now click on search

		driver.findElement(By.id("SearchBtn")).click();

		waitFor(5000);

		// verify that result appears for the provided journey search

		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// close the browser

		driver.quit();

	}

	private void waitFor(int durationInMilliSeconds) {

		try {

			Thread.sleep(durationInMilliSeconds);

		} catch (InterruptedException e) {

			e.printStackTrace();
			// To change body of catch statement use File | Settings | File Templates.

		}

	}

	private boolean isElementPresent(By by) {

		try {

			driver.findElement(by);

			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	private void setDriverPath() {

		if (PlatformUtil.isMac()) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");

		}
		if (PlatformUtil.isWindows()) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		}
		if (PlatformUtil.isLinux()) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_linux");

		}
	}

}
