package com.bts.db.service;

import com.bts.db.Dto.*;
import com.bts.db.domain.*;
import com.bts.db.repository.LikeRepository;
import com.bts.db.repository.NftRepository;
import com.bts.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NftServiceImpl implements NftService{
    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Override
    public HashMap<String, String> saveNft(NFTDto Nftdto) {
        User user = userRepository.findByuserId(Nftdto.getOwner()).orElse(null);

        NFT nft = NFT.builder().id(Nftdto.getId())
                .name(Nftdto.getName())
                .date(Nftdto.getDate())
                .description(Nftdto.getDescription())
                .image(Nftdto.getImage())
                .userId(user)
                .imagepath(Nftdto.getImagepath())
                .auction(Nftdto.getAuction())
                .price(Nftdto.getPrice())
                .term(Nftdto.getTerm())
                .build();
        nftRepository.save(nft);
        HashMap<String, String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }

    @Override
    public List<NFT> findNft(Long id) {
        User user = userRepository.findByuserId(id).orElse(null);
        List<NFT> nfts = nftRepository.findByuserId(user).orElse(null);
        return nfts;
    }

    @Override
    public List findNftByNftId(String nftid) {
        List<NFT> nfts = nftRepository.findById(nftid).orElse(null);
        return nfts;
    }
    @Override
    public List<NFT> findNftAll(){
        Optional<List<NFT>> nfts = Optional.ofNullable(nftRepository.findAll());
        return nfts.get();
    }

    @Override
    public HashMap<String, String> moveNft(SendDto sendDto) {
        List<NFT> nft = nftRepository.findById(sendDto.getId()).orElse(null);
        User user = userRepository.findByuserId(Long.parseLong(sendDto.getTo())).orElse(null);
        NFT pick = nft.get(0);
        NFT remove = pick;
        remove.setUserId(user);
        remove.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        nftRepository.save(remove);
        HashMap<String, String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }

    @Override
    public HashMap<String, String> deleteNft(DeleteDto deleteDto) {
        List<NFT> nft = nftRepository.findById(deleteDto.getId()).orElse(null);
        NFT pick = nft.get(0);
        List<Like> likes = likeRepository.findByno(pick).orElse(null);
        for (int i =0; i<likes.size();i++){
            Like like = likes.get(i);
            likeRepository.delete(like);
        }
        nftRepository.delete(pick);
        HashMap<String, String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }

    @Override
    public List<NFT> anctionstart(StartDto startDto) {
        List<NFT> nfts = nftRepository.findById(startDto.getNftid()).orElse(null);
        nfts.get(0).setAuction(startDto.getAuction());
        nftRepository.save(nfts.get(0));
        return nfts;
    }
    @Override
    public HashMap<String,String> auctionfinish(FinishDto finishDto) {
        List<NFT> nfts = nftRepository.findById(finishDto.getId()).orElse(null);
        nfts.get(0).setAuction(finishDto.getAuction());
        nftRepository.save(nfts.get(0));
        HashMap<String,String> result = new HashMap<>();
        result.put("status","complete");
        return result;
    }


}
