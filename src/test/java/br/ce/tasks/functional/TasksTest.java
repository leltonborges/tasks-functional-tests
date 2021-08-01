package br.ce.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acceptApp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		
//		System.setProperty("webdriver.gecko.driver", "/opt/selenium/geckodriver");
//		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Test
	public void shouldSaveSuccessTasks() {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.navigate().to("http://localhost:8081/tasks");
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
	public void notShouldSaveSuccessTasksOutwithDate() {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.navigate().to("http://localhost:8081/tasks");
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
	public void notShouldSaveSuccessTasksOutwithMsg() {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.navigate().to("http://localhost:8081/tasks");
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
	public void shouldSaveSuccessTasksWithDateOld() {
		WebDriver driver = acceptApp();

		try {
			// Acesse
			driver.navigate().to("http://localhost:8081/tasks");
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

}
