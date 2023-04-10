package org.gettingAllAuctionInformation.utilities;

import org.gettingAllAuctionInformation.domain.Auction;

public class Utils {
    
    public void fillAuction(Auction auction){

        if (auction.getCountry().isEmpty()){
            auction.setCountry("0");
        }
        if(auction.getCountyName().isEmpty()){
            auction.setCountyName("0");
        }
        if (auction.getCountyURL().isEmpty()){
            auction.setCountyURL("0");
        }
        
        if(auction.getAuctionStatus().isEmpty()){
            auction.setAuctionStatus("0");
        }
        if (auction.getAuctionDate().isEmpty()){
            auction.setAuctionDate("0");
        }
        if (auction.getFinalAmount().isEmpty()){
            auction.setFinalAmount("0");
        }
        if (auction.getSoldTo().isEmpty()){
            auction.setSoldTo("0");
        }
        
        if (auction.getAuctionType().isEmpty()){
            auction.setAuctionType("0");
        }
        if (auction.getCaseNumber().isEmpty()){
            auction.setCaseNumber("0");
        }
        if (auction.getFinalJudgmentAmount().isEmpty()){
            auction.setFinalJudgmentAmount("0");
        }
        if (auction.getParcelID().isEmpty()){
            auction.setParcelID("0");
        }
        if (auction.getPropertyAddress().isEmpty()){
            auction.setPropertyAddress("0");
        }
        if (auction.getAssessedValue().isEmpty()){
            auction.setAssessedValue("0");
        }
        if (auction.getPlaintiffMaxBid().isEmpty()){
            auction.setPlaintiffMaxBid("0");
        }
        if (auction.getZillowSaleEstimation() == null){
            auction.setZillowSaleEstimation("0");
        }
        if (auction.getZillowRentEstimation() == null){
            auction.setZillowRentEstimation("0");
        }
        if (auction.getZillowFullURL() == null || auction.getZillowFullURL().isEmpty()){
            auction.setZillowFullURL("0");
        }
        if (auction.getAttomLotType() == null){
            auction.setAttomLotType("0");
        }
        if (auction.getAttomPropLandUse() == null){
            auction.setAttomPropLandUse("0");
        }
        if (auction.getAttomPropType()  == null){
            auction.setAttomPropType("0");
        }
        if (auction.getAttomPropClass() == null){
            auction.setAttomPropClass("0");
        }
        if (auction.getAttomFrameType()  == null){
            auction.setAttomFrameType("0");
        }
        if (auction.getAttomWallType()  == null){
            auction.setAttomWallType("0");
        }
        if (auction.getAttomFloorsType()  == null){
            auction.setAttomFloorsType("0");
        }
        if (auction.getAttomConstructionType()  == null){
            auction.setAttomConstructionType("0");
        }
        if (auction.getAttomEventEstimationDate()  == null){
            auction.setAttomEventEstimationDate("0");
        }
        if (auction.getAttomConstructionCondition()  == null){
            auction.setAttomConstructionCondition("0");
        }
    }
}
