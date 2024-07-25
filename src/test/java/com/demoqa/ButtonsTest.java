package com.demoqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;

public class ButtonsTest {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/buttons";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void clickOnButtons() throws InterruptedException {

        //DOUBLE CLICK
        // localize the  first button and click on it as it's name describes
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));

        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickBtn")));

        // we use action class to double click our button

        Actions click2 = new Actions(driver);
        click2.doubleClick(doubleClickButton).perform();



        //RIGHT CLICK
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));

        Wait<WebDriver> wait1 =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickBtn")));
        Actions clickRight = new Actions(driver);
        clickRight.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
        Thread.sleep(2000);

        //CLICK ME

       WebElement clickMeButton= driver.findElement(By.linkText("Click Me"));
       Thread.sleep(2000);

        //Wait<WebDriver> wait2 =new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uTY0n")));
        clickMeButton.click();

       //Actions clickMe = new Actions(driver);
       //clickMe.clickAndHold().perform();



        // 1.check landing page

        String expectedUrl = "https://demoqa.com/buttons";
        String actualUrl = driver.getCurrentUrl();
        org.junit.Assert.assertEquals(expectedUrl, actualUrl);

        // 2.
        // check DOUBLE CLICK succes message

        WebElement clickDoubleMessage = driver.findElement(By.id("doubleClickMessage"));
        String expectedMessage="You have done a double click";
        String actualMessage= clickDoubleMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage);


        // check RIGHT CLICK succes message
        WebElement rightCLickMessage = driver.findElement(By.id("rightClickMessage"));
        String secondExpectedMessage= "You have done a right click";
        String secondActualMessage  = rightCLickMessage.getText();
        Assert.assertEquals(secondActualMessage,secondExpectedMessage);

        // check CLICK ME succes message








    }



    @Test
    public void buttonDoubleClickError(){

        //localize the button and clickit once

        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        Actions click1 =new Actions(driver);
        click1.click(doubleClickButton).perform();

        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickBtn")));

        // check if succes message is shown





















    }


}
