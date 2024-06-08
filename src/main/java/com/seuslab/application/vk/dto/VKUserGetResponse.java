package com.seuslab.application.vk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VKUserGetResponse implements VKResponse {
    private Long id;

    @JsonProperty("bdate")
    private String bdate;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("city")
    private VKCityDTO city;

    @JsonProperty("contacts")
    private VKMobilePhoneDTO contacts;

    @JsonProperty("can_access_closed")
    private boolean canAccessClosed;

    @JsonProperty("is_closed")
    private boolean isClosed;
}
