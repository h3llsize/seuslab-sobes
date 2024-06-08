package com.seuslab.application.repo;

import com.seuslab.application.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u.id from UserEntity u where u.firstName is null")
    List<Long> getNotWrittenEntities();
}
