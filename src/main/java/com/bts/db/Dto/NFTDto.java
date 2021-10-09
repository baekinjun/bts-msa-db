package com.bts.db.Dto;


import com.bts.db.domain.Auction;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class NFTDto {
    private String id;
    private String name;
    private String description;
    private String image;
    private Long owner;
    private String date;
    private String imagepath;
    private Auction auction;
    private String price;
    private Integer term;
}
