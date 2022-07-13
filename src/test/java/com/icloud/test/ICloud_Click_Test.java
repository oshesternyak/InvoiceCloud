package com.icloud.test;

import com.icloud.test.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ICloud_Click_Test {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test_click() {

        String url = TestConfig.getInstance().getMainURL();
        driver.get(url);

        WebElement addBox = driver.findElement(By.xpath("//button[@onclick='addElement()']"));

        int numberOfElements = 3;
        for (int i = 0; i < numberOfElements; i++) {
            addBox.click();
        }

        List<WebElement> webElements = awaitForElementList(driver, "//div[@id='elements']/button[@class='added-manually']", 3, 10);
        Assert.assertEquals(webElements.size(), 3);

    }

    private List<WebElement> awaitForElementList(WebDriver driver, String selector, int numberOfElements, int timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSec);
        ExpectedCondition<List<WebElement>> condition = new ExpectedCondition<List<WebElement>>() {
            @Override
            public @Nullable List<WebElement> apply(@Nullable WebDriver webDriver) {
                List<WebElement> elements = driver.findElements(By.xpath(selector));
                return elements.size() == numberOfElements ? elements : null;
            }
        };
        return wait.until(condition);
    }

    @AfterMethod
    public void tearDown (){
        driver.quit();
    }

}

