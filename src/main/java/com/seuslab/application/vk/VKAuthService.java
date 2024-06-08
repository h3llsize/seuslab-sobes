package com.seuslab.application.vk;

import com.seuslab.application.utils.HeaderRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VKAuthService {

    @Value("${vk.service_token}")
    private String serviceToken;

    public RestTemplate addAuth(RestTemplate restTemplate) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer " + serviceToken));

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
