package org.gettingAllAuctionInformation.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.lang.NonNull;
import org.gettingAllAuctionInformation.domain.Auction;
import org.json.JSONArray;
import org.json.JSONObject;

public class ZillowCrawling {

    @NonNull
    public void getFullZillowInformation(Auction auction) throws UnirestException {
        String propertyAddress = auction.getPropertyAddress();
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://www.zillow.com/homes/" + propertyAddress).asString();
        String responseBody = response.getBody();
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> responseTwo = Unirest.post("https://html2json.com/api/v1")
                .header("Content-Type", "text/plain")
                .body(responseBody)
                .asJson();
        JSONObject responseData = (JSONObject) responseTwo.getBody().getObject().get("data");
        JSONObject responseHead = (JSONObject) responseData.get("head");
        JSONArray responseMeta = (JSONArray) responseHead.get("meta");
        for (int i = 0; i < responseMeta.length(); i++) {
            if (responseMeta.get(i).toString().contains("og:url")) {
                try {
                    JSONObject objectWithURL = (JSONObject) responseMeta.get(i);
                    auction.setZillowFullURL(objectWithURL.get("content").toString());
                    int startingOf_Zpid = auction.getZillowFullURL().indexOf("_zpid/");
                    String zpid = auction.getZillowFullURL().substring(startingOf_Zpid - 8, startingOf_Zpid);
                    auction.setZillowZpidNumber(zpid);
                } catch (Exception e) {
                }
            }
            if (responseMeta.get(i).toString().contains("og:title")) {
                try {
                    JSONObject titleForAddress = (JSONObject) responseMeta.get(i);
                    String address = titleForAddress.get("content").toString();
                    System.out.println(address);
                    auction.setAddressOne(address.substring(0, address.indexOf(",")));
                    auction.setAddressTwo(address.substring(auction.getAddressOne().length() + 1, address.indexOf("|") - 1));
                } catch (Exception e) {
                }
            }
            if (responseMeta.get(i).toString().contains("zillow_fb:beds")) {
                try {
                    JSONObject objectWithNumberOfBeds = (JSONObject) responseMeta.get(i);
                    auction.setZillowBeds(Integer.parseInt(objectWithNumberOfBeds.get("content").toString()));
                } catch (Exception e) {
                }
            }
            if (responseMeta.get(i).toString().contains("zillow_fb:baths")) {
                try {
                    JSONObject objectWithNumberOfBaths = (JSONObject) responseMeta.get(i);
                    auction.setZillowBeds(Integer.parseInt(objectWithNumberOfBaths.get("content").toString()));
                } catch (Exception e) {
                }
            }
        }
    }
}



