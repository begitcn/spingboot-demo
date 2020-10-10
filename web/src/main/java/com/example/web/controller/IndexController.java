package com.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例
 *
 * @author barista
 */
@RestController
@RequestMapping("/test")
public class IndexController {

    @GetMapping
    public String getMapping(){
        return "hello world ! get request";
    }
}
