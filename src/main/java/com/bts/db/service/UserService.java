package com.bts.db.service;

import com.bts.db.domain.Oauth2AttributeDto;
import com.bts.db.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public interface UserService {
    User userSaveOrUpdate(Oauth2AttributeDto oauth2AttributeDto);
    Optional<User> findByEmailAndSocial(String email, String social);
    HashMap<String,String> findById(Long id);
    HashMap<String, String> insertwallet(HashMap<String,String> wallet);
    HashMap<String, String> findaddressbyemail(String email);

}
