package org.gettingAllAuctionInformation.utilities;

import org.gettingAllAuctionInformation.domain.Auction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ClerkCrawlingFunctions {

    public ClerkCrawlingFunctions() {
    }

    private static final String ENTER_THE_AUCTION_CALENDAR_ID = "splashMenuBottom";
    private static final String FIRST_DATE_AVAILABLE_CSS_SELECTOR = "#MAIN_TBL_CONTENT > div.FIXEDWIDTHCONTENT.calDiv > div > div.CALDAYBOX > div.CALBOX.CALW6.CALSELF";
    private static final String NUMBER_OF_PAGES_PER_DATA = "maxCA";

    public ChromeDriver enterToTheClerkWebsite(String clerkURL) {
        System.setProperty("webdriver.chrome.driver", "//Users/davidshoval/Downloads/chromedriver_mac_arm64 (1)/chromedriver");

        ChromeOptions options = new ChromeOptions();
        ChromeDriver chromeDriver = new ChromeDriver(options.addArguments("--remote-allow-origins=*"));
        chromeDriver.get(clerkURL);
        return chromeDriver;
    }

    public void enterTheAuctionPage(ChromeDriver chromeDriver) throws InterruptedException {
        chromeDriver.findElement(By.id(ENTER_THE_AUCTION_CALENDAR_ID)).click();
        TimeUnit.SECONDS.sleep(5);

        chromeDriver.findElement(By.cssSelector(FIRST_DATE_AVAILABLE_CSS_SELECTOR)).click();
        TimeUnit.SECONDS.sleep(5);
    }

    public int getNumberOfPagesPerAuctionDate(ChromeDriver chromeDriver) {
        String numberAsAText = chromeDriver.findElement(By.id(NUMBER_OF_PAGES_PER_DATA)).getText();
        if (numberAsAText.isBlank()){
            return 1;
        } else {
            return Integer.parseInt(chromeDriver.findElement(By.id(NUMBER_OF_PAGES_PER_DATA)).getText());
        }
    }

    public void leftSideAuctionDivInformation(Auction auction, WebElement auctionStats) {
        auction.setAuctionStatus(auctionStats.findElement(By.cssSelector("div.ASTAT_MSGA.ASTAT_LBL")).getText());
        auction.setAuctionDate(auctionStats.findElement(By.cssSelector("div.ASTAT_MSGB.Astat_DATA")).getText());
        auction.setFinalAmount(auctionStats.findElement(By.cssSelector("div.ASTAT_MSGD.Astat_DATA")).getText());
        auction.setSoldTo(auctionStats.findElement(By.cssSelector("div.ASTAT_MSG_SOLDTO_MSG.Astat_DATA")).getText());
    }

    public void rightSideAuctionDivInformation(Auction auction, WebElement auctionDetailes){

        List<WebElement> numberOfTrs = auctionDetailes.findElements(By.cssSelector("tr"));
        for (int z = 0; z < numberOfTrs.size(); z++) {
            String th = (numberOfTrs.get(z).findElement(By.cssSelector("th")).getText());
            String td = (numberOfTrs.get(z).findElement(By.cssSelector("td")).getText());
            switch (th){
                case "Auction Type:":
                    auction.setAuctionType(td);
                case "Case #:":
                    auction.setCaseNumber(td);
                case "Final Judgment Amount:":
                    auction.setFinalJudgmentAmount(td);
                case "Parcel ID:":
                    auction.setParcelID(td);
                case "Property Address:":
                    auction.setPropertyAddress(td);
                case "":
                    if (!auction.getPropertyAddress().isEmpty()){
                        auction.setPropertyAddress(auction.getPropertyAddress() + " " + td);
                    }
                case "Assessed Value:":
                    auction.setAssessedValue(td);
                case "Plaintiff Max Bid:":
                    auction.setPlaintiffMaxBid(td);
                default: break;
            }
        }
    }
}
