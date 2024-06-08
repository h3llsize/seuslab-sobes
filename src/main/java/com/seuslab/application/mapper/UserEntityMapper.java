package com.seuslab.application.mapper;

import com.seuslab.application.entity.UserEntity;
import com.seuslab.application.vk.dto.VKUserGetResponse;
import org.springframework.stereotype.Component;

//Можно было воспользоваться MapStruct, знаем и умеем. С помощью него можно будет отрефакторить сервис, но я решил написать самую простую реализацию.
@Component
public class UserEntityMapper {
    public UserEntity toEntity(VKUserGetResponse vkUserGetResponse) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(vkUserGetResponse.getId());
        userEntity.setBirthDate(vkUserGetResponse.getBdate());
        userEntity.setContacts(vkUserGetResponse.getContacts() != null ? vkUserGetResponse.getContacts().getMobilePhoneNumber() : null);
        userEntity.setCity(vkUserGetResponse.getCity() != null ? vkUserGetResponse.getCity().getTitle() : null);
        userEntity.setFirstName(vkUserGetResponse.getFirstName());
        userEntity.setLastName(vkUserGetResponse.getLastName());

        return userEntity;
    }
}
