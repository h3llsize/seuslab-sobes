package com.seuslab.application.vk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seuslab.application.vk.dto.VKRequest;
import com.seuslab.application.vk.dto.VKResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@Service
public class VKRequestService {

    private final RestTemplate restTemplate;

    public VKRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T extends VKResponse> T sendRequest(VKRequest request, Class<T> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> options = objectMapper.convertValue(request, new TypeReference<>() {});

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(VKApiConst.API_URL + request.getMethodName());
            options.forEach(builder::queryParam);

            return restTemplate.getForObject(builder.toUriString(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
