package org.gettingAllAuctionInformation.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "Counties")
public class County {

        private String country;
        private String county;
        private String clerkURL;
}
