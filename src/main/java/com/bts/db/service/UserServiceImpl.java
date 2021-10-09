package com.bts.db.service;

import com.bts.db.domain.Oauth2AttributeDto;
import com.bts.db.domain.User;
import com.bts.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User userSaveOrUpdate(Oauth2AttributeDto oauth2AttributeDto) {
        User user = saveOrUpdate(oauth2AttributeDto);
        return user;
    }

    @Override
    public Optional<User> findByEmailAndSocial(String email, String social) {
        Optional<User> userOptional = userRepository.findByEmailAndSocial(email, social);
        return userOptional;
    }

    @Override
    public HashMap<String, String> insertwallet(HashMap<String, String> wallet) {
        Long id = Long.parseLong(wallet.get("id"));
        Optional<User> user = userRepository.findByuserId(id);
        System.out.println(user);
        user.ifPresent(selectUser -> {
            selectUser.updatewallet(wallet.get("address"));
            userRepository.save(selectUser);
        });
        HashMap<String, String> status = new HashMap<>();
        status.put("status","OK");
        return status;
    }
    @Override
    public HashMap<String, String> findaddressbyemail(String email) {
        User user = userRepository.findByemail(email).orElse(null);
        HashMap<String, String> result = new HashMap<>();
        result.put("address",user.getCoinWallet().toString());
        result.put("id",user.getUserId().toString());
        return result;
    }

    @Override
    public HashMap<String,String> findById(Long id) {
        User user =  userRepository.findById(id).orElse(User.builder().name("null").build());
        HashMap<String,String> result = new HashMap<>();
        if(user.getName().equals("null") || user.getCoinWallet() == null)
        {
            result.put("address","");
        }
        else {
            result.put("address", user.getCoinWallet());
        }
        return result;
    }

    private User saveOrUpdate(Oauth2AttributeDto attribute) {
        User user = userRepository.findByEmailAndSocial(attribute.getEmail(), attribute.getAttributeKey())
                .map(entity -> entity.update(attribute.getName(), attribute.getPicture(), attribute.getAttributeKey()))
                .orElse(attribute.toEntity());
        return userRepository.save(user);
    }


}
