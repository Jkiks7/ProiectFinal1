package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SliderBarTest {
    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        // Open page
        String url = "https://demoqa.com/slider";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
   public void moveTheSlider() throws InterruptedException {

        //check landing page
        String expectedUrl="https://demoqa.com/slider";
        String actualUrl  = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

        // locate the slider element on the webpage
        WebElement sliderContainer = driver.findElement(By.xpath("//*[@id=\"sliderContainer\"]/div[1]/span"));

        Actions moveSlider = new Actions(driver);
         //first position "25"
       moveSlider.dragAndDropBy(sliderContainer,-125,0).perform();

       // move slider back to 0
       moveSlider.dragAndDropBy(sliderContainer,-250,0).perform();

       // move slider to 91
       moveSlider.dragAndDropBy(sliderContainer,200,0).perform();

       // verify if the sliding was made and stopped at 91

        WebElement sliderValue = driver.findElement(By.id("sliderValue"));
       Thread.sleep(4000);
        String actualValue=sliderValue.getAttribute("value");
        String expectedValue="91";
        Assert.assertEquals(actualValue,expectedValue);

    }

       @Test
    public void changeValueManually(){

        // check if we can change manually the slider's value

           WebElement sliderValue = driver.findElement(By.id("sliderValue"));
          sliderValue.sendKeys("29");

           String initialValue= sliderValue.getAttribute("value");
           String updatedValue=sliderValue.getAttribute("value");

           Assert.assertEquals(initialValue,updatedValue);
           System.out.println("Slider value can't be changed manually!");

       }

       @AfterTest(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
       }

    }





