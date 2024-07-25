package com.demoqa;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SliderTest {
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
        WebElement sliderContainer = driver.findElement(By.xpath("//*[@id=\"sliderContainer\"]/div[1]/span"));
        Actions moveSlider = new Actions(driver);

        //first position 25
       moveSlider.dragAndDropBy(sliderContainer,-125,0).perform();


        // slide back 0
       moveSlider.dragAndDropBy(sliderContainer,-250,0).perform();

       // slide to 91
       moveSlider.dragAndDropBy(sliderContainer,200,0).perform();


       WebElement sliderValue = driver.findElement(By.id("sliderValue"));
       Thread.sleep(4000);
        String actualValue=sliderValue.getAttribute("value");
        String expectedValue="91";
        Assert.assertEquals(actualValue,expectedValue);

    }

       @Test
    public void changeValueManual(){

           WebElement sliderValue = driver.findElement(By.id("sliderValue"));
          sliderValue.sendKeys("29");

           String initialValue= sliderValue.getAttribute("value");
           String updatedValue=sliderValue.getAttribute("value");

           Assert.assertEquals(initialValue,updatedValue);
           System.out.println("Slider value can't be changed manually!");

       }












        //for (int i=0;i<101;i++){

        //}
    }





    //sliderContainer
    //sliderValue

