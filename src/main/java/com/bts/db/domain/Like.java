package com.bts.db.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity @Getter
@Table(name = "likelist")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="number")
    private Integer number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="NO")
    private NFT no;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userId;

    @Builder
    public Like(Integer number, NFT No, User userId){
        this.number = number;
        this.no = No;
        this.userId = userId;
    }


}
