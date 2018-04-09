package com.jitb2c.sso.service.impl;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.mapper.TbUserMapper;
import com.jitb2c.pojo.TbUser;
import com.jitb2c.pojo.TbUserExample;
import com.jitb2c.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理Service
 * @Author wuqiong6
 * @Date 2018/4/9 13:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

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
}
