package com.seuslab.application.vk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seuslab.application.vk.VKApiConst;
import lombok.Data;

import java.util.List;

@Data
public class VKUserGetRequest implements VKRequest {

    @JsonProperty("user_ids")
    private String userIds;

    @JsonProperty("fields")
    private String fields = VKApiConst.FIELDS;

    @JsonProperty("name_case")
    private String nameCase;

    @JsonProperty("from_group_id")
    private String fromGroupId;

    @JsonProperty("v")
    private String v = "5.199";

    public void setUserIds(List<Long> userIds) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < userIds.size(); i++) {
            stringBuilder.append(userIds.get(i) + ",");
        }

        this.userIds = stringBuilder.toString();
    }

    @Override
    public String getMethodName() {
        return "users.get";
    }
}
