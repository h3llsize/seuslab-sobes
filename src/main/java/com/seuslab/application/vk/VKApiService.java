package com.seuslab.application.vk;

import com.seuslab.application.exception.VKRequestException;
import com.seuslab.application.vk.dto.VKUserGetRequest;
import com.seuslab.application.vk.dto.VKUserGetResponse;
import com.seuslab.application.vk.dto.VKUserGetResponseList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VKApiService {
    private final VKRequestService vkRequestService;

    public VKApiService(VKRequestService vkRequestService) {
        this.vkRequestService = vkRequestService;
    }

    public VKUserGetResponse userGetRequest(Long userId) {
        VKUserGetRequest vkUserGetRequest = new VKUserGetRequest();
        vkUserGetRequest.setUserIds(List.of(userId));

        return vkRequestService.sendRequest(vkUserGetRequest, VKUserGetResponseList.class).getResponse().get(0);
    }

    public List<VKUserGetResponse> userGetRequest(List<Long> userId) {

        if(userId.size() > 1000) {
            throw new VKRequestException("List of user ids more than 1000");
        }

        VKUserGetRequest vkUserGetRequest = new VKUserGetRequest();
        vkUserGetRequest.setUserIds(userId);

        return vkRequestService.sendRequest(vkUserGetRequest, VKUserGetResponseList.class).getResponse();
    }
}
