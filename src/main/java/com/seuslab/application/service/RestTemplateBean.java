package com.seuslab.application.service;

import com.seuslab.application.vk.VKAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBean {

    private final VKAuthService vkAuthService;

    public RestTemplateBean(VKAuthService vkAuthService) {
        this.vkAuthService = vkAuthService;
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        vkAuthService.addAuth(restTemplate);

        return restTemplate;
    }
}
