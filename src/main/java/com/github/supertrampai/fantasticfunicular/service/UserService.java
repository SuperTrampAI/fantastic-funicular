package com.github.supertrampai.fantasticfunicular.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.supertrampai.fantasticfunicular.CusException;
import com.github.supertrampai.fantasticfunicular.domain.User;
import com.github.supertrampai.fantasticfunicular.dto.PageOut;
import com.github.supertrampai.fantasticfunicular.dto.UserInputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserSerachInputDto;
import com.github.supertrampai.fantasticfunicular.repository.UserMapper;
import com.github.supertrampai.fantasticfunicular.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Component
public class UserService {

    //private static UserMapper userMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  /*  @Transactional// 事务
    public void demo(Object...obj){

    }*/
    //UserInputDto dto  dto.getPageNum(), dto.getPageSize()
    public PageOut<UserOutputDto> list(UserSerachInputDto dto) {
        PageInfo<UserOutputDto> page = PageHelper.startPage(1,10)
                .doSelectPageInfo(() -> userMapper.list());
        return new PageOut<>(page);
    }
    public PageOut<UserOutputDto> getAll(UserSerachInputDto dto) {
        PageInfo<UserOutputDto> page = PageHelper.startPage(1,10)
                .doSelectPageInfo(() -> userMapper.getAll());
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
    @Transactional
    public void saveUser(UserInputDto dto){
        User user;
        Date now=new Date();
        if ( dto.getId()==null) { // 新增
            user = new User();
            user.setCreatedt(now);
        } else { // 修改
            user = userRepository.findById(dto.getId())
                    .orElseThrow(() -> new CusException(  "该数据不存在"));
            user.setUpdatedt(now);
        }
        BeanUtils.copyProperties(dto, user);
        User newUser=userRepository.save(user);
        redisTemplate.opsForValue().set(newUser.getId()+"",newUser.toString());
    }

    public User getUserById(Integer uid){
        User user =userRepository.findById(uid).orElseThrow(()->new CusException("该用户不存在"));
        return user;
    }
    /* ----------------------------- */

    /**
     * 创建对象
     * @param user
     */
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }
}
