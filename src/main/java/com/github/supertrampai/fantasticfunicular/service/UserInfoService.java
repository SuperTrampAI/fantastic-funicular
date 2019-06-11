package com.github.supertrampai.fantasticfunicular.service;

import com.github.supertrampai.fantasticfunicular.domain.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}