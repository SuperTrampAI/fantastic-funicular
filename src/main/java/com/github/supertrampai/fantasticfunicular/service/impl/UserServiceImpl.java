package com.github.supertrampai.fantasticfunicular.service.impl;

import com.github.supertrampai.fantasticfunicular.domain.User;
import com.github.supertrampai.fantasticfunicular.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-06-12 12:20
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Service
public class UserServiceImpl {
    //该类不是使用的Impl

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void edit(User user) {
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
