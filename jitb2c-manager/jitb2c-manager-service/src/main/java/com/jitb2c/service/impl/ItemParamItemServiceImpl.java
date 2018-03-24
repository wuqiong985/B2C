package com.jitb2c.service.impl;

import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.mapper.TbItemParamItemMapper;
import com.jitb2c.pojo.TbItemParamItem;
import com.jitb2c.pojo.TbItemParamItemExample;
import com.jitb2c.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author wuqiong6
 * @Date 2018/3/24 17:30
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {


    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    /**
     * 根据商品Id获取商品规格参数 完成商品展示功能
     * @param itemId
     * @return
     */
    @Override
    public String getItemParamItemByItemId(long itemId) {
        //根据商品Id获取商品规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria= example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (null == list || list.size() == 0){
            return "";
        }
        //取规格参数信息
        TbItemParamItem tbItemParamItem = list.get(0);
        String paramData = tbItemParamItem.getParamData();
        //生成html
        StringBuffer sb = new StringBuffer();
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        //第一层遍历，及便利分组
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            //第二层遍历，遍历每个分组下的规格项和规格参数
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
