package com.bts.db.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Oauth2AttributeDto {
    private String attributeKey;
    private String email;
    private String name;
    private String picture;

    public User toEntity() {
        return User.builder().social(attributeKey).name(name).email(email).picture(picture).role(Role.USER).build();
    }
}
