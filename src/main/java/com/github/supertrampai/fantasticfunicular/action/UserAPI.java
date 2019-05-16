package com.github.supertrampai.fantasticfunicular.action;

import com.github.supertrampai.fantasticfunicular.Resp;
import com.github.supertrampai.fantasticfunicular.dto.PageOut;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import com.github.supertrampai.fantasticfunicular.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Validated
//@CrossOrigin
@RestController
@RequestMapping("/users")
@Api(description = "用户")
public class UserAPI {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/ordBailList",method=RequestMethod.POST)
    @ApiOperation(value = "用户列表")//@RequestBody UserInputDto dto
    public Resp<PageOut<UserOutputDto>> ordBailList(){
        return new Resp<>(userService.list());
    }

}
