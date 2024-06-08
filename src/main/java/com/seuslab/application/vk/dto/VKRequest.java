package com.seuslab.application.vk.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface VKRequest {
    @JsonIgnore
    String getMethodName();

}
