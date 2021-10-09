package com.bts.db.Dto;

import com.bts.db.domain.Role;
import com.bts.db.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {
    private Long id;
    private String social;
    private String name;
    private String email;
    private String picture;
    private String coinWallet;
    private Role role;

    public UserDto toDto(User user) {
        return UserDto.builder().id(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .coinWallet(user.getCoinWallet())
                .picture(user.getPicture())
                .role(user.getRole())
                .social(user.getSocial()).build();
    }

}
