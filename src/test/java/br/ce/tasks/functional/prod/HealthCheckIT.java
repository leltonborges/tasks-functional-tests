package br.ce.tasks.functional.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

	@Test
	public void healthChech() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.67:4444/wd/hub"), cap);
		try {
			driver.navigate().to("http://192.168.1.67:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version_build = driver.findElement(By.id("version")).getText();

			Assert.assertTrue(version_build.contains("build_"));
		} finally {
			driver.quit();
		}
	}
}