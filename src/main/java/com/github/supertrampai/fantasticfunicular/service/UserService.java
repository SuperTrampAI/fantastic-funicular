package com.github.supertrampai.fantasticfunicular.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.supertrampai.fantasticfunicular.dto.PageOut;
import com.github.supertrampai.fantasticfunicular.dto.UserInputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import com.github.supertrampai.fantasticfunicular.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    //private static UserMapper userMapper;
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  /*  @Transactional// 事务
    public void demo(Object...obj){

    }*/

    public PageOut<UserOutputDto> list(UserInputDto dto) {
        PageInfo<UserOutputDto> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize())
                .doSelectPageInfo(() -> userMapper.list());
        return new PageOut<>(page);
    }

    public PageInfo<UserOutputDto> findMessageListByPage(UserInputDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<UserOutputDto> user = userMapper.list();
        for(UserOutputDto u:user) {
            System.out.println(u.getName());
        }
        return new PageInfo<UserOutputDto>(userMapper.list());
    }



}
