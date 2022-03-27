package com.uni.jobfinder.services.impl;

import com.uni.jobfinder.configuration.SeleniumConfiguration;
import com.uni.jobfinder.models.Offer;
import com.uni.jobfinder.services.JobsBGService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class JobsBGServiceImpl implements JobsBGService {

    // constants for jobs.bg website address and css selectors
    private static final String WEBSITE_ADDRESS = "https://www.jobs.bg/front_job_search.php";
    private static final String OFFERS_SEARCH_CSS_SELECTOR = "#listContainer > ul.page-1 > li.\\3";
    private static final String OFFER_TITLE_CSS_SELECTOR = "> div > div > div > div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-7 > a > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)";
    private static final String OFFER_SUBTITLE_CSS_SELECTOR = " > div > div > div > div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-7 > a > div:nth-child(2) > div.card__subtitle";
    private static final String OFFER_URL_CSS_SELECTOR = "> div > div > div > div.mdc-layout-grid__cell.mdc-layout-grid__cell--span-7 > a";

    // get all offers from jobs.bg page
    @Override
    public String getAllOffers(int timeout) {

        // new selenium drive for jobs.bg with timeout
        SeleniumConfiguration seleniumConfiguration = new SeleniumConfiguration(timeout);
        WebDriver driver = seleniumConfiguration.getNewDriver(WEBSITE_ADDRESS);

        return getOffersFromPage(20, driver);
    }

    // extract offers from the page to a string
    private String getOffersFromPage(int offers, WebDriver driver) {
        String allOffers = "";
//        List<Offer> offerList = new ArrayList<>();

        // jobs.bg shows 20 offers per page, but once the offer reaches over 10th,
        // a whitespace is added between the tens(a) and the ones(b)
        for (int i = 1; i <= offers; i++) {
            int a = i / 10; //tens
            int b = i % 10; //ones
            if (i < 10) {
//                offerList.add(getSingleDigitOffer(driver, b));
                allOffers = allOffers + getSingleDigitOffer(driver, b);
            } else {
//                offerList.add(getDoubleDigitOffer(driver, a, b));
                allOffers = allOffers + getDoubleDigitOffer(driver, a, b);
            }
        }
        driver.close();
        return allOffers;
    }

    // get offers from jobs.bg page up to the desired total amount
    @Override
    public String getLimited(int total, int timeout) {

        // new selenium drive for jobs.bg with timeout
        SeleniumConfiguration seleniumConfiguration = new SeleniumConfiguration(timeout);
        WebDriver driver = seleniumConfiguration.getNewDriver(WEBSITE_ADDRESS);

        System.out.println("\n\nExtracting the first " + total + " offers from page 1 of jobs.bg\n");
        return getOffersFromPage(total, driver);
    }

    // get offers 1-9 from jobs.bg
    private Offer getSingleDigitOffer(WebDriver driver, int a) {
        Offer offer = new Offer();
        offer.setDescription(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + OFFER_TITLE_CSS_SELECTOR)).getText());
        offer.setPositionName(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + OFFER_TITLE_CSS_SELECTOR)).getText());
        offer.setLink(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR +  a + OFFER_URL_CSS_SELECTOR)).getAttribute("href"));
        offer.setRef(getRef(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR +  a + OFFER_URL_CSS_SELECTOR)).getAttribute("href")));
        offer.setLocation(getLocation(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + OFFER_SUBTITLE_CSS_SELECTOR)).getText()));
        offer.setSalary(getSalary(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + OFFER_SUBTITLE_CSS_SELECTOR)).getText()));
        return offer;
    }

    // get offers 10-20 from jobs.bg
    private Offer getDoubleDigitOffer(WebDriver driver, int a, int b) {
        Offer offer = new Offer();
        offer.setDescription(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + " " + b + OFFER_TITLE_CSS_SELECTOR)).getText());
        offer.setPositionName(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + " " + b + OFFER_TITLE_CSS_SELECTOR)).getText());
        offer.setLink(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR +  a + " " + b + OFFER_URL_CSS_SELECTOR)).getAttribute("href"));
        offer.setRef(getRef(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR +  a + " " + b + OFFER_URL_CSS_SELECTOR)).getAttribute("href")));
        offer.setLocation(getLocation(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + " " + b + OFFER_SUBTITLE_CSS_SELECTOR)).getText()));
        offer.setSalary(getSalary(driver.findElement(By.cssSelector(OFFERS_SEARCH_CSS_SELECTOR + a + " " + b + OFFER_SUBTITLE_CSS_SELECTOR)).getText()));
        return offer;
    }

    // get the reference number of the offer
    private String getRef(String url) {
        return url.substring(url.indexOf("/") + 18);
    }

    // get the location of the offer
    private String getLocation(String subtitle) {
        int index = subtitle.indexOf(";");
        if (index != -1) {
            return subtitle.substring(0, index);
        } else {
            return subtitle;
        }
    }

    // get the offered salary of the offer
    private String getSalary(String subtitle) {
        int salaryStart = subtitle.indexOf("Заплата ");
        int salaryEnd = subtitle.indexOf("BGN");
        if (salaryStart != -1 && salaryEnd != -1) {
            return (subtitle.substring(salaryStart + 8, salaryEnd) + "BGN");
        }
        return "Not Specified";
    }

}
