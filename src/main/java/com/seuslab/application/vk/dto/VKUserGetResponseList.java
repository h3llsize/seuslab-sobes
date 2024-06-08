package com.seuslab.application.vk.dto;

import lombok.Data;

import java.util.List;

@Data
public class VKUserGetResponseList implements VKResponse{
    private List<VKUserGetResponse> response;
}
