package com.example.web.controller;

import com.example.web.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @RestController 示例
 * 当前类中方法全部返回json格式数据
 * 相当于@Controller + @ResponseBody
 *
 * @author barista
 */
@RestController
public class IndexController {

    @GetMapping("/hello")
    public Object getMapping() {
        return "hello world ! get request";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("James", "man");
        return user;
    }
}
