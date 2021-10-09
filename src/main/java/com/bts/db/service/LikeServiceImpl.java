package com.bts.db.service;

import com.bts.db.domain.Like;
import com.bts.db.Dto.LikeDto;
import com.bts.db.domain.NFT;
import com.bts.db.domain.User;
import com.bts.db.repository.LikeRepository;
import com.bts.db.repository.NftRepository;
import com.bts.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;

    public HashMap<String,String> likenft(LikeDto likedto)
    {
        NFT nft = nftRepository.findById(likedto.getNftid()).get().get(0);
        User user = userRepository.findByuserId(Long.parseLong(likedto.getUser())).orElse(null);
        Like like = Like.builder().No(nft).userId(user).build();
        likeRepository.save(like);
        HashMap<String,String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }

    @Override
    public List<Like> likelist(String user) {
        User id = userRepository.findByuserId(Long.parseLong(user)).orElse(null);
        List<Like> likes = likeRepository.findByuserId(id).orElse(null);
        return likes;
    }

    @Override
    public HashMap<String, String> deletelikenft(LikeDto likedto) {
        NFT nft = nftRepository.findById(likedto.getNftid()).get().get(0);
        User user = userRepository.findByuserId(Long.parseLong(likedto.getUser())).orElse(null);
        List<Like> like = likeRepository.findByuserId(user).orElse(null);
        Like deletelike = new Like();
        for(int i = 0 ; i<like.size();i++){
            if(like.get(i).getNo().getId()==nft.getId()){
                deletelike = Like.builder().number(like.get(i).getNumber()).No(like.get(i).getNo()).userId(user).build();
                break;
            }

        }
        likeRepository.delete(deletelike);
        HashMap<String,String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }

    @Override
    public HashMap<String, Integer> countlike(String Nftid) {
        List<Like> likes = likeRepository.findAll();
        Integer count = 0;
        for(int i=0; i<likes.size();i++){
            if(likes.get(i).getNo().getId().equals(Nftid))
            {
                count++;
            }
        }
        HashMap<String, Integer> result = new HashMap<>();
        result.put("count",count);
        return result;
    }
}
