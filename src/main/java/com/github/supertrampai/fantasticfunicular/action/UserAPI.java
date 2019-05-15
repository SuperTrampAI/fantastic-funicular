package com.github.supertrampai.fantasticfunicular.action;

import com.github.supertrampai.fantasticfunicular.Resp;
import com.github.supertrampai.fantasticfunicular.dto.PageOut;
import com.github.supertrampai.fantasticfunicular.dto.UserInputDto;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import com.github.supertrampai.fantasticfunicular.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Validated
//@CrossOrigin
@RestController
@RequestMapping("/users")
@Api(description = "用户")
public class UserAPI {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/ordBailList",method=RequestMethod.POST)
    @ApiOperation(value = "用户列表")
    public Resp<PageOut<UserOutputDto>> ordBailList(Integer admid, @RequestBody UserInputDto dto){
        return new Resp<>(userService.list(dto));
    }

}
