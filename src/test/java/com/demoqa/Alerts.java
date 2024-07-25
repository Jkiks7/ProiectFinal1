package com.demoqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Alerts {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/alerts";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void alertButton() throws InterruptedException {

        // check landing page
        String expectedUrl = "https://demoqa.com/alerts";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

        Thread.sleep(2000);

        WebElement clickButton = driver.findElement(By.id("alertButton"));
        clickButton.click();

        Thread.sleep(3000);

        Alert message = driver.switchTo().alert();
        String expectedAlertMessage = "You clicked a button";
        String actualAlertMessage = driver.switchTo().alert().getText(); // capture alert message

        Thread.sleep(2000);

        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);

        message.accept();

    }

    @Test(priority = 2)
    public void timerAlertButton() throws InterruptedException {
        WebElement onClickButton = driver.findElement(By.id("timerAlertButton"));
        onClickButton.click();

        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert timerAlert = driver.switchTo().alert();
        Thread.sleep(5000);

        String expectedTimerMessage = "This alert appeared after 5 seconds";
        String actualTimerMessage = driver.switchTo().alert().getText();

        Assert.assertEquals(actualTimerMessage, expectedTimerMessage);

        timerAlert.accept();

    }

    @Test(priority = 3)
    public void confirmButton() throws InterruptedException {
        WebElement confirmButton = driver.findElement(By.id("confirmButton"));
        confirmButton.click();
        Thread.sleep(3000);

        Alert confirmAction = driver.switchTo().alert();
        String expectedAlert = "Do you confirm action?";
        String actualAlert = driver.switchTo().alert().getText();

        Assert.assertEquals(actualAlert, expectedAlert);

        confirmAction.accept();

        WebElement confirmResult = driver.findElement(By.id("confirmResult"));

        String expectedOkMessage = "You selected Ok";
        String actualOkMessage = confirmResult.getText();
        Assert.assertEquals(actualOkMessage, expectedOkMessage);

    }

    @Test(priority = 4)
    public void cancelConfirmButton() throws InterruptedException {
        WebElement confirmButton = driver.findElement(By.id("confirmButton"));
        confirmButton.click();
        Thread.sleep(3000);

        Alert cancelAction = driver.switchTo().alert();
        cancelAction.dismiss();
        Thread.sleep(2000);

        WebElement dismissResult = driver.findElement(By.id("confirmResult"));
        String expectedDismissMessage = "You selected Cancel";
        String actualDismissMessage = dismissResult.getText();

        Assert.assertEquals(actualDismissMessage, expectedDismissMessage);

    }

     @Test(priority = 5)
     public void promptBoxButton() throws InterruptedException {
        WebElement promptButton= driver.findElement(By.xpath("//*[@id=\"promtButton\"]"));

        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"promtButton\"]")));
        promptButton.click();

        Thread.sleep(2000);

        Alert promptBox =driver.switchTo().alert();
        String expectedRequest="Please enter your name";
        String actualRequest  = promptBox.getText();

        Assert.assertEquals(actualRequest,expectedRequest);

        Thread.sleep(2000);

        promptBox.sendKeys("Kara");
        Thread.sleep(2000);
        promptBox.accept();

        WebElement promptResult= driver.findElement(By.id("promptResult"));

        String expectedEnteredMessage= "You entered Kara";
        String actualEnteredMessage  = promptResult.getText();
        Assert.assertEquals(actualEnteredMessage,expectedEnteredMessage);

     }

     @AfterTest(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.close();
     }

}



