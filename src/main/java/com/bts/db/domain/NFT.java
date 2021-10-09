package com.bts.db.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@Entity
@Table(name = "NFT")
@Getter @Setter
@NoArgsConstructor
public class NFT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO", nullable = false)
    private Long NO;
    @Column(name = "id",unique = true)
    private String id;
    private String name;
    private String description;
    private String image;
    private String date;
    private Auction auction;
    @Column(name = "startprice")
    private String price;
    private String imagepath;
    private Integer term;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userId;

    @Builder
    public NFT (String id, String name, String description, String image, User userId, String date, String imagepath, Auction auction, String price,Integer term)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.userId = userId;
        this.date = date;
        this.imagepath = imagepath;
        this.auction = auction;
        this.price = price;
        this.term = term;
    }


}
