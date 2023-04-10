package org.gettingAllAuctionInformation.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.gettingAllAuctionInformation.domain.County;
import org.gettingAllAuctionInformation.repository.CountyRepository;
import org.gettingAllAuctionInformation.service.AuctionCrawling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class StartCrawling {

    @Autowired
    private final AuctionCrawling auctionCrawling;
    @Autowired
    private final CountyRepository countyRepository;

    @GetMapping("/start")
    public void startCrawlingNow() throws UnirestException, IOException, InterruptedException {
        for (int i = 0; i < countyRepository.count(); i++) {
            Optional<County> county = Optional.ofNullable(countyRepository.findAll().get(i));
            auctionCrawling.auctionCrawlingEnterTheFirstPage(county.get().getCountry(), county.get().getCounty(),
                    county.get().getClerkURL());
        }
    }
}

