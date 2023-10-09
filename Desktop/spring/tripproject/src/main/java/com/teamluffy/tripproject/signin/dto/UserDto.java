package com.teamluffy.tripproject.signin.dto;

import com.teamluffy.tripproject.signin.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userName;

    public static UserDto toUserDTO(UserEntity userEntity) {
        UserDto userDTO = new UserDto();
        userDTO.setId(userEntity.getId());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPassword(userEntity.getUserPassword());
        userDTO.setUserName(userEntity.getUserName());

        return userDTO;
    }
}
