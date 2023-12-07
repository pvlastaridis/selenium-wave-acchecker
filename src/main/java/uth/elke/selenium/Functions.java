package uth.elke.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Functions {
    public static void getWaveWebAIM(String line) {
        WebDriver driver = new FirefoxDriver();

        String[] array = line.split("\t");
        driver.get("https://wave.webaim.org/report#/" + array[1]);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> elements = driver.findElements(By.id("viewdetails"));


        if (!elements.isEmpty()) elements.get(0).click();

        File scrFile = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshotsWAVE\\" +
                    array[0] + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }

    public static void getACchecker(String line) {
        WebDriver driver = new FirefoxDriver();

        String[] array = line.split("\t");
        driver.get("https://achecks.org/checker/index.php" + array[1]);

        WebElement textbox = driver.findElement(By.id("checkuri"));

        List<WebElement> elements = driver.findElements(By.id("validate_uri"));
        WebElement img = driver.findElement(By.id("toggle_image"));
        WebElement anch = img.findElement(By.xpath("following-sibling::*"));
        anch.click();

        List<WebElement> radio_wcag3 = driver.findElements(By.id("radio_gid_9"));
        textbox.sendKeys(array[1]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (!radio_wcag3.isEmpty()) radio_wcag3.get(0).click();
        if (!elements.isEmpty()) elements.get(0).click();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        File scrFile = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshotsACchecker\\" +
                    array[0] + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
