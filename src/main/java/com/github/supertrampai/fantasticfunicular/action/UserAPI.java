package com.github.supertrampai.fantasticfunicular.action;

import com.github.supertrampai.fantasticfunicular.Resp;
import com.github.supertrampai.fantasticfunicular.domain.User;
import com.github.supertrampai.fantasticfunicular.dto.PageOut;
import com.github.supertrampai.fantasticfunicular.dto.UserInputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserSerachInputDto;
import com.github.supertrampai.fantasticfunicular.service.RedisService;
import com.github.supertrampai.fantasticfunicular.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

//@Validated
//@CrossOrigin
@RestController
@RequestMapping("/users")
@Api(description = "用户")
public class UserAPI {

    @Autowired
    UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/userList",method=RequestMethod.POST)
    @ApiOperation(value = "用户列表")//
   // @Cacheable(value="user-key")  // 暂时有问题，需要修改
    public Resp<PageOut<UserOutputDto>> list(@RequestBody UserSerachInputDto dto){
        return new Resp<>(userService.list(dto));
    }

    @RequestMapping(value = "/getAll",method=RequestMethod.POST)
    @ApiOperation(value = "用户列表")//
    // @Cacheable(value="user-key")  // 暂时有问题，需要修改
    public Resp<PageOut<UserOutputDto>> getAll(@RequestBody UserSerachInputDto dto){
        return new Resp<>(userService.getAll(dto));
    }

    @RequestMapping(value = "/saveUser",method=RequestMethod.POST)
    @ApiOperation(value = "add/update user")//
    //@CacheEvict(value="dto", key="#dto.id")
    public Resp<?> saveUser(@RequestBody UserInputDto dto){
        userService.saveUser(dto);
        return new Resp<>();
    }
    @RequestMapping(value="/user",method=RequestMethod.GET)
    @ApiOperation(value = "用户列表")
    @ApiImplicitParam(name="id",value="查询ID",required=true)
   // @Cacheable(value="user-key",key = "#id")
    public Resp<User> getUserId(@RequestParam Integer id){
        return new Resp<>(userService.getUserById(id));
    }

}
