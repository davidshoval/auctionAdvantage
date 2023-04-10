package org.gettingAllAuctionInformation.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.gettingAllAuctionInformation.domain.Auction;
import org.json.JSONArray;

public class Attom {

    public void getExpandedProfile(Auction auction) throws UnirestException {
        String adderssOne = auction.getAddressOne().replaceAll("\\s", "");
        String addressTwo = auction.getAddressTwo().replaceAll("\\s", "");
        System.out.println("this is address one: " +
                adderssOne + " and this is address two: " +
                addressTwo);
        String requestUrl = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/expandedprofile?address1=" +
                adderssOne + "&address2=" + addressTwo;
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(requestUrl)
                .header("apikey", "e4fc20cf4731cbee468c6dec6881b4ff")
                .asJson();
        try {
            JSONArray propertyArray = response.getBody().getObject().getJSONArray("property");
            try {
                auction.setAttomLotSizeSqft(propertyArray.getJSONObject(0).getJSONObject("lot").getDouble("lotSize2"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomID(propertyArray.getJSONObject(0).getJSONObject("identifier").getInt("attomId"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomTaxCodeArea(propertyArray.getJSONObject(0).getJSONObject("area").getInt("taxCodeArea"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomPropLandUse(propertyArray.getJSONObject(0).getJSONObject("summary").getString("propLandUse"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomPropType(propertyArray.getJSONObject(0).getJSONObject("summary").getString("propType"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomYearBuilt(propertyArray.getJSONObject(0).getJSONObject("summary").getInt("yearBuilt"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomPropClass(propertyArray.getJSONObject(0).getJSONObject("summary").getString("propClass"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomTaxYear(propertyArray.getJSONObject(0).getJSONObject("assessment").getJSONObject("tax").getInt("taxYear"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomTaxYear(propertyArray.getJSONObject(0).getJSONObject("assessment").getJSONObject("tax").getInt("taxAmt"));

            } catch (Exception e) {
            }
            try {
                auction.setAttomLatitude(propertyArray.getJSONObject(0).getJSONObject("location").getDouble("latitude"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomLongitude(propertyArray.getJSONObject(0).getJSONObject("location").getDouble("longitude"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBathsPartial(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("rooms").getInt("bathsPartial"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBathsFull(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("rooms").getInt("bathsFull"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBathTotal(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("rooms").getInt("bathsTotal"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBeds(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("rooms").getDouble("beds"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomGrossSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("size").getDouble("grossSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBuildingSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("size").getDouble("bldgSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomLivingSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("size").getDouble("livingSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomUniversalSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("size").getDouble("universalSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomConstructionType(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("construction").getString("constructionType"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomFrameType(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("construction").getString("frameType"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomWallType(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("construction").getString("wallType"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomFloorsType(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("interior").getString("floors"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBasmentSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("interior").getDouble("bsmtSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomBasmentPrecentageFinished(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("interior").getInt("bsmtFinishedPercent"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomConstructionCondition(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("construction").getString("condition"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomPropertyStructureMajorImprovementsYear(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("construction").getInt("propertyStructureMajorImprovementsYear"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomParkingSize(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("parking").getDouble("prkgSize"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomParkingSpaces(propertyArray.getJSONObject(0).getJSONObject("building").getJSONObject("parking").getDouble("prkgSpaces"));
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    public void getAvm(Auction auction) throws UnirestException {
        String adderssOne = auction.getAddressOne().replaceAll("\\s", "");
        String addressTwo = auction.getAddressTwo().replaceAll("\\s", "");
        System.out.println("this is address one: " +
                adderssOne + " and this is address two: " +
                addressTwo);
        String requestUrl = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/attomavm/detail?address1=" +
                adderssOne + "&address2=" + addressTwo;
        System.out.println(requestUrl);
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(requestUrl)
                .header("apikey", "e4fc20cf4731cbee468c6dec6881b4ff")
                .asJson();
        try {
            JSONArray propertyArray = response.getBody().getObject().getJSONArray("property");
            try {
                auction.setAttomEstimateScore(propertyArray.getJSONObject(0).getJSONObject("avm").getJSONObject("amount").getInt("scr"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomEstimateValue(propertyArray.getJSONObject(0).getJSONObject("avm").getJSONObject("amount").getInt("value"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomEstimateHighValue(propertyArray.getJSONObject(0).getJSONObject("avm").getJSONObject("amount").getInt("high"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomEstimateLowValue(propertyArray.getJSONObject(0).getJSONObject("avm").getJSONObject("amount").getInt("low"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomEstimateFDS(propertyArray.getJSONObject(0).getJSONObject("avm").getJSONObject("amount").getInt("fsd"));
            } catch (Exception e) {
            }
            try {
                auction.setAttomAssessedTotalValue(propertyArray.getJSONObject(0).getJSONObject("assessment").getJSONObject("assessed").getInt("assdttlvalue"));
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    public void getRentalAvm(Auction auction) throws UnirestException {
        String adderssOne = auction.getAddressOne().replaceAll("\\s", "");
        String addressTwo = auction.getAddressTwo().replaceAll("\\s", "");
        System.out.println("this is address one: " +
                adderssOne + " and this is address two: " +
                addressTwo);
        String requestUrl = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/valuation/rentalavm?address1=" +
                adderssOne + "&address2=" + addressTwo;
        System.out.println(requestUrl);
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(requestUrl)
                .header("apikey", "e4fc20cf4731cbee468c6dec6881b4ff")
                .asJson();
        try {
            JSONArray propertyArray = response.getBody().getObject().getJSONArray("property");
            try {
                auction.setAttomEstimatedRentalValue(propertyArray.getJSONObject(0).getJSONObject("rentalAvm").getInt("estimatedRentalValue"));
            } catch (Exception e) {
            }
            try {
                auction.setGetAttomEstimatedMinRentalValue(propertyArray.getJSONObject(0).getJSONObject("avm").getInt("estimatedMinRentalValue"));
            } catch (Exception e) {
            }
            try {
                auction.setGetAttomEstimatedMaxRentalValue(propertyArray.getJSONObject(0).getJSONObject("avm").getInt("estimatedMaxRentalValue"));
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }
}