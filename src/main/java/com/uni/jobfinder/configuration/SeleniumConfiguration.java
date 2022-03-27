package com.uni.jobfinder.configuration;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class SeleniumConfiguration {

    int timeout;

    public SeleniumConfiguration(int timeout) {
        this.timeout = timeout;
    }

    // create a selenium chromedriver for the desired website address
    private WebDriver createDriver(String url) {
        System.setProperty("webdriver.chrome.driver", ".\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }

    public WebDriver getNewDriver(String url) {
        return createDriver(url);
    }

}
