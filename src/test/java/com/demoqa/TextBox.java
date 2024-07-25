package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TextBox {
    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/text-box";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();


    }


    @Test
    public void completeTextBox(){
        WebElement inputName= driver.findElement(By.id("userName"));
        inputName.sendKeys("Ion Popescu");

        WebElement emailAddress = driver.findElement(By.id("userEmail"));
        emailAddress.sendKeys("ionpopescu@yahoo.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys(" Str. Maxim Gorki nr.3");


        WebElement permanentAddress = driver.findElement((By.id("permanentAddress")));
        permanentAddress.sendKeys(" Str. Ialomitei nr.15");

        //verify the action of the submitting button

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        Assert.assertTrue(submitButton.isEnabled());

        //check landing page

        String expectedUrl = "https://demoqa.com/text-box";
        String actualUrl = driver.getCurrentUrl();
        org.junit.Assert.assertEquals(expectedUrl, actualUrl);



        WebElement outputField= driver.findElement(By.id("output"));
        Assert.assertTrue(outputField.isDisplayed());

        // check if correct name is shown  after submitting

        WebElement completedName = driver.findElement(By.id("name"));
        String expectedName = "Ion Popescu";
        String actualName = completedName.getAccessibleName();
        Assert.assertTrue(completedName.isDisplayed());

        // check email is correct

        WebElement completedEmail = driver.findElement(By.id("email"));
        String expectedEmail = "ionpopescu@yahoo.com";
        String actualEmail   = completedEmail.getText();
        Assert.assertTrue(completedEmail.isDisplayed());

        // check current addres
        WebElement existingCurrentAddress = driver.findElement(By.id("currentAddress"));
        String expectedCurrentAddress = " Str.Maxim Gorki nr.3";
        String actualCurrentAddress   = existingCurrentAddress.getText();
        Assert.assertTrue(existingCurrentAddress.isDisplayed());

        //check permanent addres

        WebElement existingPermanentAddress = driver.findElement(By.id("permanentAddress"));
        String expectedPermanentAddress = " Str. Ialomitei nr.15";
        String actualPermanentAddres    = existingPermanentAddress.getText();
        Assert.assertTrue(existingPermanentAddress.isDisplayed());

        //check that current addres is not the same with permanent address

        Assert.assertNotEquals(expectedCurrentAddress,expectedPermanentAddress);


    }

}


