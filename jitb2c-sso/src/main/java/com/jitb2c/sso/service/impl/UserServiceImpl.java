package com.jitb2c.sso.service.impl;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.mapper.TbUserMapper;
import com.jitb2c.pojo.TbUser;
import com.jitb2c.pojo.TbUserExample;
import com.jitb2c.sso.dao.JedisClient;
import com.jitb2c.sso.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户管理Service
 * @Author wuqiong6
 * @Date 2018/4/9 13:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public JitB2CResult checkData(String content, Integer type) {

        //创建查询条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        //对数据进行校验 可选参数1、2、3分别代表username、phone、email
        if ( 1 == type){
            //用户名校验
            criteria.andUsernameEqualTo(content);
        } else if ( 2 == type){
            //电话校验
            criteria.andPhoneEqualTo(content);
        } else {
            //邮箱校验
            criteria.andEmailEqualTo(content);
        }

        //判断是否查到值
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null ||list.size() == 0){
            return JitB2CResult.ok(true);
        }
        return JitB2CResult.ok(false);
    }

    @Override
    public JitB2CResult createUser(TbUser user) {

        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return JitB2CResult.ok();
    }

    @Override
    public JitB2CResult userLogin(String userName, String password) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);

        List<TbUser> list = userMapper.selectByExample(example);

        //用户不存在
        if (null == list || list.size() ==0){
            return JitB2CResult.build(400,"用户名或者密码错误");
        }
        TbUser user = list.get(0);
        //密码校验
        if ( !DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return JitB2CResult.build(400,"用户名或者密码错误");
        }

        //生成token
        String token = UUID.randomUUID().toString();
        //保存用户之前把用户对象中的密码清空
        user.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置session过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,SSO_SESSION_EXPIRE);
        //返回token
        return JitB2CResult.ok(token);
    }
}
