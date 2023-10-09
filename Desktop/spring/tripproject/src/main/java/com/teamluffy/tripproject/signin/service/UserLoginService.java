package com.teamluffy.tripproject.signin.service;

import com.teamluffy.tripproject.signin.dto.UserDto;
import com.teamluffy.tripproject.signin.entity.UserEntity;
import com.teamluffy.tripproject.signin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;

    public void save(UserDto userDTO) {
        // repository의 save 메서드 호출
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

    public UserDto login(UserDto userDTO) {
        Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());
        if (byUserEmail.isPresent()) {
            UserEntity userEntity = byUserEmail.get();
            if (userEntity.getUserPassword().equals(userDTO.getUserPassword())) {
                UserDto dto = UserDto.toUserDTO(userEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
