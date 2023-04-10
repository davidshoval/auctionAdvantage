package org.gettingAllAuctionInformation.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.gettingAllAuctionInformation.domain.Auction;
import org.json.JSONArray;
import org.json.JSONObject;

public class ZillowApi {

    private final String ZILLOW_API_KEY_SERVER_TOKEN = "b6a394a6051f94d3709474a23e2b60c3";

    public void getZillowZestimate(Auction auction) throws UnirestException {
        String url = "https://api.bridgedataoutput.com/api/v2/zestimates_v2/zestimates?access_token="
                + ZILLOW_API_KEY_SERVER_TOKEN + "&zpid=" + auction.getZillowZpidNumber();
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(url)
                .asJson();
        try {
            JSONObject bundleArray = response.getBody().getObject().getJSONArray("bundle").getJSONObject(0);
            try {
                auction.setZillowLowPercent(bundleArray.getInt("lowPercent"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowHighPercent(bundleArray.getInt("highPercent"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowZestimate(bundleArray.getInt("zestimate"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowMinus30(bundleArray.getInt("minus30"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowRentalLowPercent(bundleArray.getInt("rentalLowPercent"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowRentalZestimate(bundleArray.getInt("rentalZestimate"));
            } catch (Exception e) {
            }
            try {
                auction.setZillowRentalHighPercent(bundleArray.getInt("rentalHighPercent"));
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }
}
