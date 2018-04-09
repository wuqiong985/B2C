package com.jitb2c.portal.service.impl;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.HttpClientUtil;
import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.pojo.TbItemDesc;
import com.jitb2c.pojo.TbItemParam;
import com.jitb2c.pojo.TbItemParamItem;
import com.jitb2c.portal.pojo.ItemInfo;
import com.jitb2c.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 前台商品相关信息查询展示Service
 * @Author wuqiong6
 * @Date 2018/4/8 16:19
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INF0_URL}")
    private String ITEM_INF0_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;
    /**
     * 取商品基本信息
     * @param itemId 商品Id
     * @return
     */
    @Override
    public ItemInfo getItemById(long itemId) {
        try{
        //调用rest的服务，查询商品基本信息
        String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INF0_URL+itemId);
        if (!StringUtils.isBlank(json)){
            JitB2CResult result = JitB2CResult.formatToPojo(json,ItemInfo.class);
            if (result.getStatus() == 200){
                ItemInfo item = (ItemInfo) result.getData();
                return item;
            }
        }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 取商品描述
     * @param itemId 商品Id
     * @return
     */
    @Override
    public String getItemDescById(long itemId) {
        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            //转换为java对象
            if (!StringUtils.isBlank(json)){
                JitB2CResult result = JitB2CResult.formatToPojo(json, TbItemDesc.class);
                if (result.getStatus() == 200){
                    TbItemDesc itemDesc = (TbItemDesc) result.getData();
                    //取商品描述信息
                    String desc = itemDesc.getItemDesc();
                    return desc;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getItemParam(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换为Java对象
            JitB2CResult result = JitB2CResult.formatToPojo(json,TbItemParamItem.class);
            if (result.getStatus() == 200){
                TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                StringBuffer sb = new StringBuffer();
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
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

                //返回html片段
                return sb.toString();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


}
