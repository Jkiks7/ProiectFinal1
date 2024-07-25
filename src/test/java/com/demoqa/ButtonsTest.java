package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
        // locate the  first button and click on it as it's name describes
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));

        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickBtn")));

        // we use action class to double-click our button

        Actions click2 = new Actions(driver);
        click2.doubleClick(doubleClickButton).perform();



        //RIGHT CLICK
        // locate second button and click on it as it's name describes
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));

        Wait<WebDriver> wait1 =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickBtn")));

        Actions clickRight = new Actions(driver);

        clickRight.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
        Thread.sleep(2000);

        //CLICK ME
        // locate third button and click on it as it's name describes

       WebElement clickMeButton= driver.findElement(By.cssSelector("[class][class='mt-4']:nth-child(4) .btn-primary"));


        Wait<WebDriver> wait2 =new WebDriverWait(driver, Duration.ofSeconds(30));
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class][class='mt-4']:nth-child(4) .btn-primary")));
        clickMeButton.click();



        // 1.check landing page

        String expectedUrl = "https://demoqa.com/buttons";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);


        // 2.check DOUBLE CLICK success message

        WebElement clickDoubleMessage = driver.findElement(By.id("doubleClickMessage"));
        String expectedMessage="You have done a double click";
        String actualMessage= clickDoubleMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage);


        //3. check RIGHT CLICK success message
        WebElement rightCLickMessage = driver.findElement(By.id("rightClickMessage"));
        String secondExpectedMessage = "You have done a right click";
        String secondActualMessage   = rightCLickMessage.getText();
        Assert.assertEquals(secondActualMessage,secondExpectedMessage);

        //4. check CLICK ME success message
        WebElement clickMeMessage  = driver.findElement(By.id("dynamicClickMessage"));
        String thirdExpectedMessage="You have done a dynamic click";
        String thirdActualMessage  = clickMeMessage.getText();
        Assert.assertEquals(thirdActualMessage,thirdExpectedMessage);



    }
    @Test
    public void simpleClick() throws InterruptedException {

        driver.navigate().refresh();

        //try simple click on first button
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        doubleClickButton.click();
        Assert.assertFalse(doubleClickButton.isSelected());
        Thread.sleep(1000);

        // check if there is any message present after simple clicking
        String messageToCheck = "You have done a double click";
        boolean isMessageOnPage = driver.getPageSource().contains(messageToCheck);
        Assert.assertFalse(isMessageOnPage,"You have done a double click");


        //try simple click on second button
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
        rightClickButton.click();
        Assert.assertFalse(rightClickButton.isSelected());
        Thread.sleep(1000);

        // check if there is any message present after simple clicking
        String secondMessageToCheck="You have done a right click";
        boolean isSecondMessageOnPage=driver.getPageSource().contains(secondMessageToCheck);
        Assert.assertFalse(isSecondMessageOnPage,"You have done a right click");


    }
    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(6000);
        driver.close();
    }
}

