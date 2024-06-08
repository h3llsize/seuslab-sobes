package com.seuslab.application.vk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VKMobilePhoneDTO {
    @JsonProperty("mobile_phone")
    private String mobilePhoneNumber;

    @JsonProperty("home_phone")
    private String homePhoneNumber;
}
