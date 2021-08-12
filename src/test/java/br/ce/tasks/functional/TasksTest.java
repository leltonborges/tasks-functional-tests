package br.ce.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acceptApp() throws MalformedURLException {
//		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//		WebDriver driver = new ChromeDriver();
//		System.setProperty("webdriver.gecko.driver", "/opt/selenium/geckodriver");
//		WebDriver driver = new FirefoxDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.67:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.67:8085/tasks");
		return driver;
	}

	@Test
	public void shouldSaveSuccessTasks() throws MalformedURLException {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Click button
			driver.findElement(By.id("addTodo")).click();

			// add tasks
			driver.findElement(By.id("task")).sendKeys("Test Selenium");

			driver.findElement(By.id("dueDate")).sendKeys("05/01/2040");

			// save
			driver.findElement(By.id("saveButton")).click();

			// validating
//			String msg = driver.findElement(By.id("message")).getText();

//			Assert.assertEquals("Success!", msg);

		} finally {
//			 close browser
			driver.quit();
		}
	}

	@Test
	public void notShouldSaveSuccessTasksOutwithDate() throws MalformedURLException {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Click button
			driver.findElement(By.id("addTodo")).click();

			// add tasks
			driver.findElement(By.id("task")).sendKeys("Test Selenium");

			// save
			driver.findElement(By.id("saveButton")).click();

			// validating
			String msg = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the due date", msg);
		} finally {
			// close browser
			driver.quit();
		}
	}

	@Test
	public void notShouldSaveSuccessTasksOutwithMsg() throws MalformedURLException {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Click button
			driver.findElement(By.id("addTodo")).click();

			// add tasks
			driver.findElement(By.id("dueDate")).sendKeys("05/01/2022");

			// save
			driver.findElement(By.id("saveButton")).click();

			// validating
			String msg = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the task description", msg);
		} finally {
			// close browser
			driver.quit();
		}
	}

	
	@Test
	public void shouldSaveSuccessTasksWithDateOld() throws MalformedURLException {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Click button
			driver.findElement(By.id("addTodo")).click();

			// add tasks
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("05/01/2001");

			// save
			driver.findElement(By.id("saveButton")).click();

			// validating
			String msg = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Due date must not be in past", msg);
		} finally {
			// close browser
			driver.quit();
		}
	}
	
	@Test
	public void removeSuccessTasks() throws MalformedURLException {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Click button
			driver.findElement(By.id("addTodo")).click();

			// add tasks
			driver.findElement(By.id("task")).sendKeys("Test Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("05/01/2050");

			// save
			driver.findElement(By.id("saveButton")).click();
			
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm'][text() = 'Remove']")).click();
		} finally {
			// close browser
			driver.quit();
		}
	}

}
