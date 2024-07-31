package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class BrowserWindows {
    WebDriver driver;
    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/browser-windows";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void newTab() throws InterruptedException {

        //check landing page
        String actualUrl=driver.getCurrentUrl();
        String expectedUrl="https://demoqa.com/browser-windows";
        Assert.assertEquals(actualUrl,expectedUrl);

        // click on first tab button
        WebElement clickTab= driver.findElement(By.id("tabButton"));
        clickTab.click();

        //after new tab opens switch the driver to new tab using the object class from selenium

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        //make sure that the driver switched to the new tab
        String actualNewTab = driver.getCurrentUrl();
        String expectedNewTab="https://demoqa.com/sample";
        Assert.assertEquals(actualNewTab,expectedNewTab);

        //locate the header element on the new webpage
        WebElement samplePage= driver.findElement(By.id("sampleHeading"));

        String actualPageTitle = samplePage.getText();
        String expectedPageTitle="This is a sample page";
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        Thread.sleep(500);

        // close current driver and switch back to the base page
        driver.close();
        driver.switchTo().window((String) windowHandles[0]);

        //verify that we are on the base page
        String actualBaseUrl= driver.getCurrentUrl();
        String expectedBaseUrl="https://demoqa.com/browser-windows";
        Assert.assertEquals(actualBaseUrl,expectedBaseUrl);
        System.out.println("Base page is: "+actualBaseUrl);

    }

    @Test
    public void newWindow() throws InterruptedException {

        //click on new window button
        WebElement clickNewWindow= driver.findElement(By.id("windowButton"));
        clickNewWindow.click();

        // after new tab opens switch the driver to it using the object class
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        //make sure that the driver switched to the new window
        String actualNewWindow = driver.getCurrentUrl();
        String expectedNewWindow="https://demoqa.com/sample";
        Assert.assertEquals(actualNewWindow,expectedNewWindow);

        //locate the header element on the new page
        WebElement windowTitle=driver.findElement(By.id("sampleHeading"));

        String actualTitle= windowTitle.getText();
        String expectedTitle ="This is a sample page";
        Assert.assertEquals(actualTitle,expectedTitle);
        Thread.sleep(500);

        // close current driver and switch back to the base page
        driver.close();
        driver.switchTo().window((String) windowHandles[0]);

        //verify that we are on the base page
        String actualBaseUrl= driver.getCurrentUrl();
        String expectedBaseUrl="https://demoqa.com/browser-windows";
        Assert.assertEquals(actualBaseUrl,expectedBaseUrl);
        System.out.println("Base page is: "+actualBaseUrl);


    }
   /* @Test
    public void newWindowMessage(){

        //click on new window message button
        WebElement newWindowMessage= driver.findElement(By.id("messageWindowButton"));
        Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        newWindowMessage.click();

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        Wait<WebDriver> wait1 =new WebDriverWait(driver, Duration.ofSeconds(20));


        //make sure that the driver switched to blank page
        String actualBlankPage = driver.getCurrentUrl();
        String expectedBlankPage="https://about:blank";
        Assert.assertEquals(actualBlankPage,expectedBlankPage);

 }*/
    @AfterTest
    public void tearDown() throws InterruptedException {
     Thread.sleep(1000);
     driver.close();
    }
}
