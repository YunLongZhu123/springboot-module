package com.springboot.demo.controller;

import com.springboot.demo.bean.UserBean;
import com.springboot.demo.constant.ResponseEnum;
import com.springboot.demo.dto.LoginDto;
import com.springboot.demo.exception.MyException;
import com.springboot.demo.response.BaseResponse;
import com.springboot.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login.json")
    public String login(@RequestBody LoginDto dto) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(),dto.getPassword());
        try {
            subject.login(token);
            UserBean userName = userService.findByUserName(dto.getUsername());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user_info",userName);
            redisTemplate.opsForValue().set("user_info",userName);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        return "success";
    }

    @GetMapping("/current-user-info.json")
    @ResponseBody
    public BaseResponse<UserBean> getCurrentUserInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        UserBean user_info = (UserBean)session.getAttribute("user_info");
        if(user_info != null) {
            BaseResponse response = new BaseResponse(ResponseEnum.SUCCESS,user_info);
            return response;
        }else{
            throw new MyException("用户未登录。。。。","0000001");
        }

    }
}
