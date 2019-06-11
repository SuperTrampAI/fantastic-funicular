package com.github.supertrampai.fantasticfunicular.service.impl;

import com.github.supertrampai.fantasticfunicular.domain.UserInfo;
import com.github.supertrampai.fantasticfunicular.repository.UserInfoRepository;
import com.github.supertrampai.fantasticfunicular.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-06-12 00:15
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }
}
