package org.gettingAllAuctionInformation.repository;

import org.gettingAllAuctionInformation.domain.County;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends MongoRepository<County, String> {
    List<County> findAll();

}
