package com.github.supertrampai.fantasticfunicular.action;

import com.github.supertrampai.fantasticfunicular.domain.User;
import com.github.supertrampai.fantasticfunicular.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-06-12 13:41
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Controller
public class UserImplController {

    @Resource
    UserServiceImpl userServiceImpl;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users=userServiceImpl.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userServiceImpl.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userServiceImpl.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userServiceImpl.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userServiceImpl.delete(id);
        return "redirect:/list";
    }

}
