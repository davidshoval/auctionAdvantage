package org.gettingAllAuctionInformation.repository;

import org.gettingAllAuctionInformation.domain.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {
    List<Auction> findAll();
    Auction save (Auction entity);

   // @Query(collation = "{ county: 'en', strength: 2 }")
   // Optional<County> findByCountry(String country);
}
