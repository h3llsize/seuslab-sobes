package com.seuslab.application.service;

import com.seuslab.application.entity.UserEntity;
import com.seuslab.application.mapper.UserEntityMapper;
import com.seuslab.application.repo.UserEntityRepository;
import com.seuslab.application.vk.dto.VKUserGetResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserEntityRepository userEntityRepository;

    private final UserEntityMapper userEntityMapper;

    public UserService(UserEntityRepository userEntityRepository, UserEntityMapper userEntityMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityMapper = userEntityMapper;
    }

    public List<Long> getNotWrittenEntities() {
        return userEntityRepository.getNotWrittenEntities();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userEntityRepository.save(userEntity);
    }

    public UserEntity toUserEntity(VKUserGetResponse vkUserGetResponse) {
        return userEntityMapper.toEntity(vkUserGetResponse);
    }
}
