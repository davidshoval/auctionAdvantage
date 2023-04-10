package org.gettingAllAuctionInformation.service;


import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.gettingAllAuctionInformation.domain.Auction;
import org.gettingAllAuctionInformation.repository.AuctionRepository;
import org.gettingAllAuctionInformation.utilities.ClerkCrawlingFunctions;
import org.gettingAllAuctionInformation.utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class AuctionCrawling {
    private final AuctionRepository auctionRepository;

    public void auctionCrawlingEnterTheFirstPage(String countryName, String countyName, String clerkURL) throws InterruptedException, UnirestException, IOException {

        ClerkCrawlingFunctions clerkCrawlingFunctions = new ClerkCrawlingFunctions();

        ChromeDriver chromeDriver = clerkCrawlingFunctions.enterToTheClerkWebsite(clerkURL);

        clerkCrawlingFunctions.enterTheAuctionPage(chromeDriver);

        TimeUnit.SECONDS.sleep(5);

        for (int i = 0; i < 10; i++) {
            auctionCrawlingInsideEachDate(chromeDriver, clerkCrawlingFunctions, countryName, countyName, clerkURL);
            chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/div[2]/div[3]/div[2]/a")).click();
            TimeUnit.SECONDS.sleep(10);
        }
    }

    public void auctionCrawlingInsideEachDate(ChromeDriver chromeDriver, ClerkCrawlingFunctions clerkCrawlingFunctions, String countryName, String countyName, String clerkURL) throws InterruptedException, UnirestException, IOException {

        for (int i = 0; i < clerkCrawlingFunctions.getNumberOfPagesPerAuctionDate(chromeDriver); i++) {

            List<WebElement> numberOfAuctionsPerPage = chromeDriver.findElements(By.cssSelector("div[id*='AITEM']"));

            for (int x = 0; x < numberOfAuctionsPerPage.size(); x++) {
                Auction auction = new Auction();
                auction.setCountry(countryName);
                auction.setCountyName(countyName);
                auction.setCountyURL(clerkURL);

                WebElement auctionStats = numberOfAuctionsPerPage.get(x).findElement(By.cssSelector("div.AUCTION_STATS"));
                if (!auctionStats.findElement(By.cssSelector("div.ASTAT_MSGB.Astat_DATA")).getText().contains("Canceled")) {
                    clerkCrawlingFunctions.leftSideAuctionDivInformation(auction, auctionStats);

                    WebElement auctionDetails = numberOfAuctionsPerPage.get(x).findElement(By.cssSelector("div.AUCTION_DETAILS"));
                    clerkCrawlingFunctions.rightSideAuctionDivInformation(auction, auctionDetails);

                    ZillowCrawling zillowCrawling = new ZillowCrawling();
                    zillowCrawling.getFullZillowInformation(auction);
                    ZillowApi zillowApi = new ZillowApi();
                    zillowApi.getZillowZestimate(auction);
                    Attom attom = new Attom();
                        attom.getExpandedProfile(auction);
                        attom.getAvm(auction);
                        attom.getRentalAvm(auction);
                    new Utils().fillAuction(auction);
                    auctionRepository.save(auction);
                }
            }
            try {
                chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/div[3]/div[4]/div[3]/span[3]/img")).click();
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/div[3]/div[3]/div[3]/span[3]/img")).click();
            }
        }
    }
}


