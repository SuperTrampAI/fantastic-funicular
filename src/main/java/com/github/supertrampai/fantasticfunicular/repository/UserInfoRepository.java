package com.github.supertrampai.fantasticfunicular.repository;

import com.github.supertrampai.fantasticfunicular.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}
