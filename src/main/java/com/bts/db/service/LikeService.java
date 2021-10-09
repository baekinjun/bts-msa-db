package com.bts.db.service;

import com.bts.db.domain.Like;
import com.bts.db.Dto.LikeDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component
public interface LikeService {
    HashMap<String,String> likenft(LikeDto likedto);
    List<Like> likelist (String user);
    HashMap<String,String> deletelikenft(LikeDto likedto);
    HashMap<String,Integer>countlike(String Nftid);
}
