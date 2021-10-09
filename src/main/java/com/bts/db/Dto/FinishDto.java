package com.bts.db.Dto;

import com.bts.db.domain.Auction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinishDto {
    private String id;
    private Integer value;
    private String owner;
    private String user;
    private Auction auction;
}
