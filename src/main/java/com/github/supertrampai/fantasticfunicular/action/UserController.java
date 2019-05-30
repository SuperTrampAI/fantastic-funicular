package com.github.supertrampai.fantasticfunicular.action;

import com.github.supertrampai.fantasticfunicular.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 用户创建某本图书	POST	/books/
 * 用户修改对某本图书	PUT	/books/:id/
 * 用户删除对某本图书	DELETE	/books/:id/
 * 用户获取所有的图书 GET /books
 *  用户获取某一图书  GET /Books/:id
 * Created by fangzhipeng on 2017/4/17.
 * 官方文档：http://swagger.io/docs/specification/api-host-and-base-path/
 */
@Api(value="/user", tags="userDemo")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    @ApiOperation(value="获取y用户列表", notes="获取用户列表")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<User> getUser() {
        List<User> user = new ArrayList<User>();
        User user1=new User("name1","pwd",1,1,"nickname",1);
        User user2=new User("name1","pwd",1,1,"nickname",1);
        User user3=new User("name1","pwd",1,1,"nickname",1);
        User user4=new User("name1","pwd",1,1,"nickname",1);
        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        return user;
    }

    @ApiOperation(value="创建用户", notes="创建用户")
    @ApiImplicitParam(name = "book", value = "用户详细实体", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postBook(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }
    @ApiOperation(value="获用户细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "book", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String putUser(@PathVariable Integer id, @RequestBody User user) {
        User book1 = users.get(id);
        book1.setName(user.getName());
        book1.setName(user.getName());
        users.put(id, book1);
        return "success";
    }
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }
}

