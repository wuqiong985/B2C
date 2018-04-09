package com.jitb2c.sso.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.ExceptionUtil;
import com.jitb2c.pojo.TbUser;
import com.jitb2c.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理controller
 * @Author wuqiong6
 * @Date 2018/4/9 14:00
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,@PathVariable  Integer type,String callback){

        JitB2CResult result = null;

        //参数有效性校验
        if (StringUtils.isBlank(param)){
            result =  JitB2CResult.build(400,"校验内容不能为空");
        }
        if (type == null){
            result =  JitB2CResult.build(400,"校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3){
            result =  JitB2CResult.build(400,"校验内容类型错误");
        }

        //校验出错
        if (null != result){
            if (null != callback){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else {
                return result;
            }
        }
        //调用服务
        try {
            result = userService.checkData(param, type);
        } catch (Exception e){
            result = JitB2CResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (null != callback){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return result;
        }
    }

    //创建用户
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public JitB2CResult createUser(TbUser user){
        try {
           JitB2CResult result = userService.createUser(user);
           return result;
        } catch (Exception e){
            return JitB2CResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JitB2CResult userLogin(String username,String password){
        try {
            JitB2CResult result = userService.userLogin(username,password);
            return result;
        } catch (Exception e){
            return JitB2CResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }
}
