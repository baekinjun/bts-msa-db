package com.bts.db.service;

import com.bts.db.Dto.*;
import com.bts.db.domain.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface NftService {
    HashMap<String,String> saveNft(NFTDto nfTdto);
    List<NFT> findNft (Long userid);
    List findNftByNftId(String nftid);
    List<NFT> findNftAll();
    HashMap<String,String> moveNft(SendDto sendDto);
    HashMap<String,String> deleteNft(DeleteDto deleteDto);
    List<NFT> anctionstart(StartDto startDto);
    HashMap<String,String> auctionfinish(FinishDto finishDto);
}
