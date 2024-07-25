package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidEmail {

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
    public void wrongTypeEmail(){


        //missing the @ sign from the email address completing field  stops the submitting of the other informations too
        //the outputox is not visible

        WebElement inputName= driver.findElement(By.id("userName"));
        inputName.sendKeys("Ion Popescu");

        WebElement emailAddress = driver.findElement(By.id("userEmail"));
        emailAddress.sendKeys("ionpopescuyahoo.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys(" Str. Maxim Gorki nr.3");


        WebElement permanentAddress = driver.findElement((By.id("permanentAddress")));
        permanentAddress.sendKeys(" Str. Ialomitei nr.15");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        Assert.assertTrue(submitButton.isEnabled());


        WebElement outputField= driver.findElement(By.id("output"));
        Assert.assertFalse(outputField.isDisplayed());



    }
}
