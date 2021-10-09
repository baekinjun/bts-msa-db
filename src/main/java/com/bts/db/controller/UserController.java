package com.bts.db.controller;

import com.bts.db.Dto.*;
import com.bts.db.domain.*;
import com.bts.db.service.LikeService;
import com.bts.db.service.NftService;
import com.bts.db.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final NftService nftService;
    private final LikeService likeService;

    @PostMapping("/save-update")
    public ResponseEntity<UserDto> saveOrUpdate(@RequestBody Oauth2AttributeDto oauth2AttributeDto) throws ParseException {
        User info = userService.userSaveOrUpdate(oauth2AttributeDto);
        UserDto userInfo = new UserDto().toDto(info);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/team/{userid}")
    public ResponseEntity<HashMap<String,String>> findByaddress(@PathVariable Long userid){
        return ResponseEntity.ok(userService.findById(userid));
    }

    @PostMapping("team/wallet")
    public ResponseEntity<HashMap<String,String>> wallet(@RequestBody HashMap<String,String> wallet){
        System.out.println(wallet.toString());
        HashMap<String, String> result = userService.insertwallet(wallet);
        return ResponseEntity.ok(result);
    }
    @PostMapping("team/NFT")
    public ResponseEntity<HashMap<String,String>> saveNft(@RequestBody NFTDto Nftdto){
        System.out.println(Nftdto);
        return ResponseEntity.ok(nftService.saveNft(Nftdto));
    }
    @GetMapping("team/nft/{userid}/")
    public ResponseEntity<List<NFT>> findNft(@PathVariable Long userid){
        return ResponseEntity.ok(nftService.findNft(userid));
    }
    @GetMapping("team/nftinfo/{nftid}")
    public ResponseEntity<List<NFT>> findNftByNftId(@PathVariable String nftid)
    {
        return ResponseEntity.ok(nftService.findNftByNftId(nftid));
    }
    @GetMapping("team/allnft")
    public ResponseEntity<List<NFT>> findNftAll()
    {
        return ResponseEntity.ok(nftService.findNftAll());
    }

    @PostMapping("team/likenft")
    public ResponseEntity<HashMap<String,String>> likenft(@RequestBody LikeDto likedto){
        return ResponseEntity.ok(likeService.likenft(likedto));
    }
    @GetMapping("team/userlikelist/{user}")
    public ResponseEntity<List<Like>> likelist(@PathVariable String user){
        return ResponseEntity.ok(likeService.likelist(user));
    }
    @DeleteMapping("team/likenft")
    public ResponseEntity<HashMap<String,String>> deletelikenft(@RequestBody LikeDto likedto){
        return ResponseEntity.ok(likeService.deletelikenft(likedto));
    }
    @GetMapping("team/countlike/{Nftid}/")
    public ResponseEntity<HashMap<String,Integer>> countLike(@PathVariable String Nftid)
    {
        return ResponseEntity.ok(likeService.countlike(Nftid));
    }
    @PostMapping("team/moveNft")
    public ResponseEntity<HashMap<String, String>> moveNft(@RequestBody SendDto sendDto)
    {
        return ResponseEntity.ok(nftService.moveNft(sendDto));
    }
    @DeleteMapping("team/delete")
    public ResponseEntity<HashMap<String,String>> deleteNft(@RequestBody DeleteDto deleteDto)
    {
        return ResponseEntity.ok(nftService.deleteNft(deleteDto));
    }
    @PostMapping("team/start")
    public ResponseEntity<List<NFT>> auctionstart(@RequestBody StartDto startDto){
        return ResponseEntity.ok(nftService.anctionstart(startDto));
    }
    @GetMapping("team/email/{useremail}/")
    public ResponseEntity<HashMap<String,String>> getaddressByUserEmail(@PathVariable String useremail){
        return ResponseEntity.ok(userService.findaddressbyemail(useremail));
    }
    @PostMapping("team/finish")
    public ResponseEntity<HashMap<String,String>> auctionfinish(@RequestBody FinishDto finishdto){
        return ResponseEntity.ok(nftService.auctionfinish(finishdto));
    }

}
