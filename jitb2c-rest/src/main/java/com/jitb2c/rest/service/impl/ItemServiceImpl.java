package com.jitb2c.rest.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.mapper.TbItemDescMapper;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.mapper.TbItemParamItemMapper;
import com.jitb2c.pojo.*;
import com.jitb2c.rest.dao.JedisClient;
import com.jitb2c.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 展示商品详情页面的Service
 * 包括商品基本信息，商品描述，规格信息的查询
 * @Author wuqiong6
 * @Date 2018/4/6 21:25
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    //注入jedis，添加缓存要使用
    @Autowired
    private JedisClient jedisClient;

    @Override
    public JitB2CResult getItemBaseInfo(long itemId) {

        //添加缓存逻辑
        try{
            //从缓存中存取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            //判断是否有值
            if (!StringUtils.isBlank(json)){
                //把json字符串转换为Java对象
                TbItem item = JsonUtils.jsonToPojo(json,TbItem.class);
                return JitB2CResult.ok(item);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //根据商品id查询商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //使用Jitb2cResult包装一下
        JitB2CResult result = JitB2CResult.ok(item);

        try {
            //若缓存没有查询到，则把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JSONUtils.toJSONString(item));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JitB2CResult getItemDesc(long itemId) {

        //从缓存查询
        try{
            //从缓存中存取商品描述信息，商品id对应的描述信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isBlank(json)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json,TbItemDesc.class);
                return JitB2CResult.ok(itemDesc);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //查不到从数据库查
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        JitB2CResult result = JitB2CResult.ok(itemDesc);


        //从数据库查完之后存入缓存
        try {
            //若缓存没有查询到，则把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JitB2CResult getItemParam(long itemId) {

        //添加缓存逻辑
        try{
            //从缓存中存取商品描述信息，商品id对应的描述信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(json)){
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json,TbItemParamItem.class);
                return JitB2CResult.ok(itemParamItem);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        //list如果有值，也只有一个
        if (null != list && list.size() > 0){
            TbItemParamItem itemParamItem = list.get(0);
            //从数据库查完之后存入缓存
            try {
                //若缓存没有查询到，则把规格参数写入缓存
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JSONUtils.toJSONString(itemParamItem));
                //设置key的有效期
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param",REDIS_ITEM_EXPIRE);
            }catch (Exception e){
                e.printStackTrace();
            }
            return JitB2CResult.ok(itemParamItem);
        }


        return JitB2CResult.build(400,"无此商品规格");
    }


}
