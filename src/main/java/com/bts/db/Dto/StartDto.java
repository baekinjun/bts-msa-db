package com.bts.db.Dto;

import com.bts.db.domain.Auction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StartDto {
    private String nftid;
    private Auction auction;

    public StartDto(String nftid, Auction auction){
        this.nftid = nftid;
        this.auction=auction;
    }
}
