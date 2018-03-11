import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.pojo.TbItem;
import com.jitb2c.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author wuqiong6
 * @Date 2018/3/11 16:08
 */
public class TestPageHelper {

    @Test
    public void testPageHelper(){
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");

        //从spring容器中获得Mapper的代理对象
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);

        //执行分页，查询
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> list = mapper.selectByExample(example);

        //取商品列表
        for (TbItem tbItem : list){
            System.out.println(tbItem.getTitle());
        }

        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("商品一共有"+total+"件");
        System.out.println(pageInfo.toString());
    }
}
