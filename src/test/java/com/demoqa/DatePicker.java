package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;


public class DatePicker {
    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/date-picker";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void selectDate() {

        //check landing page
        String expectedUrl="https://demoqa.com/date-picker";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

        //locate the date picker table
        WebElement dateTable = driver.findElement(By.id("datePickerMonthYearInput"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datePickerMonthYearInput")));
        dateTable.click();


        //select month
        WebElement monthDropDown = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
        Wait<WebDriver> waitMonth = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitMonth.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));
        monthDropDown.click();

        Select selectMonth = new Select(monthDropDown);
        selectMonth.selectByVisibleText("February");

        Wait<WebDriver> waitSelection = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitSelection.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));

        WebElement selectedMonth = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select/option[2]"));

        Assert.assertTrue(selectedMonth.isSelected());

        //select Year

        WebElement yearDropDown = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select"));
        Wait<WebDriver> waitYear = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitYear.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select")));
        yearDropDown.click();

        Select selectYear = new Select(yearDropDown);
        selectYear.selectByValue("1996");

        Wait<WebDriver> waitYearIsSelected = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitYearIsSelected.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));

        WebElement selectedYear = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option[97]"));
        Assert.assertTrue(selectedYear.isSelected());

        //select day

        WebElement selectedDay = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]"));
        Wait<WebDriver> waitForDay = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitForDay.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"datePickerMonthYear\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]")));
        selectedDay.click();


        //check if selected date appears
        WebElement dateTableChanged = driver.findElement(By.xpath("//*[@id=\"datePickerMonthYearInput\"]"));
        String actualValue = dateTableChanged.getAttribute("value");
        String expectedValue = "02/07/1996";
        Assert.assertEquals(actualValue, expectedValue);
        System.out.println("Selected date is:02/07/1996");
    }


    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}










