package com.seuslab.application.service.pet;

import com.seuslab.application.entity.UserEntity;
import com.seuslab.application.service.UserService;
import com.seuslab.application.vk.VKApiService;
import com.seuslab.application.vk.dto.VKUserGetResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseFillService {

    private final VKApiService vkApiService;

    private final UserService userService;

    public BaseFillService(VKApiService vkApiService, UserService userService) {
        this.vkApiService = vkApiService;
        this.userService = userService;
    }

    public List<UserEntity> getUserEntities(List<Long> userIdList) {
        List<VKUserGetResponse> vkUserGetResponses = vkApiService.userGetRequest(userIdList);
        List<UserEntity> createdUsers = new ArrayList<>();

        for (int i = 0; i < vkUserGetResponses.size(); i++) {
            createdUsers.add(userService.toUserEntity(vkUserGetResponses.get(i)));
        }

        return createdUsers;
    }
}
