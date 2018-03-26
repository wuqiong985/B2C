# B2C
B2C毕设项目（Idea 创建）  

[MarkDown常用简单语法](https://www.jianshu.com/p/82e730892d42)

### 相关笔记

#### 一、环境搭建

##### 1.SKU:最小库存量单位  
````
      Stock Keeping Unit（库存量单位）
      对商品进行细粒度的划分  
      如 Iphone8 16G 深空灰 和 Iphone8 16G 银白色 就是两个不同的SKU
````
     
##### 2.逆向工程 
````
      Mybatis的逆向工程。根据数据库表生成java代码。
      生成过程[Idea使用mybatis插件生成逆向工程](https://segmentfault.com/a/1190000009058867)
      注意，mybatisGenerator中的mybatis-generator-config_1_0.dtd文件获取不到，直接从网上下了一个，放在resource中，直接引用位置
      详情见“E:\淘淘商城\mybatisGenerator”目录下新增的Idea工程
````

##### 3.SSM框架整合
````
    3.1	整合的思路
    
    3.1.1	Dao层
        使用mybatis框架。创建SqlMapConfig.xml。
        创建一个applicationContext-dao.xml
        1、配置数据源
        2、需要让spring容器管理SqlsessionFactory，单例存在。
        3、把mapper的代理对象放到spring容器中。使用扫描包的方式加载mapper的代理对象。
    
    3.1.2	Service层
        1、事务管理
        2、需要把service实现类对象放到spring容器中管理。
    
    3.1.3	表现层
        1、配置注解驱动
        2、配置视图解析器
        3、需要扫描controller
    
    3.1.4	Web.xml
        1、spring容器的配置
        2、Springmvc前端控制器的配置
        3、Post乱码过滤器
````

#### 二、后台管理系统

##### 4.查询商品列表实现分页
````
    使用pageHelper插件
    gitHub地址：https://github.com/pagehelper/Mybatis-PageHelper
    1.实现原理
        1.mybatis管理SqlSessionFactory
        2.SqlSessionFactory创建SqlSession
        3.SqlSession中的executor对象
        4.mybatis会将sql语句封装成MappedStatement
        5.在执行sql语句之前会执行一些mybatis拦截器
        6.在插件中实现inteceptor接口即可对sql进行扩展
        
    2.使用方法
        1.引入pageHelper的jar包
        2.在SQLMapConfig.xml中配置插件
        3.在查询的sql语句执行之前，添加一行代码
          //第一页，10行  (page,rows)
          PageHelper.startPage(1,10);
        4.执行查询
        5.取查询结果的总数量 
          PageInfo pageInfo
          pageInfo.getTotal()...
          
        注意：老版本的分页插件对逆向工程支持得不完没，有条件的不可以
````

##### 5.类目选择功能
````
    1.页面上
        使用EasyUI的异步树控件来实现：
            树控件内建异步加载模式的支持，用户先创建一个空的树，然后指定一个服务器端，执行检索后动态返回JSON数据来填充树并完成异步请求。
            
                <ul class="easyui-tree" data-options="url:'get_data.php'"></ul>  
            
            树控件读取URL。子节点的加载依赖于父节点的状态。当展开一个封闭的节点，如果节点没有加载子节点，它将会把节点id的值作为http请求参数  
            并命名为'id'，通过URL发送到服务器上面检索子节点。
           
            下面是从服务器端返回的数据：
            [{    
                "id": 1,    
                "text": "Node 1",    
                "state": "closed",    
                "children": [{    
                    "id": 11,    
                    "text": "Node 11"   
                },{    
                    "id": 12,    
                    "text": "Node 12"   
                }]    
            },{    
                "id": 2,    
                "text": "Node 2",    
                "state": "closed"   
            }] 
            
        异步tree的节点的数据结构
            {    
                "id": 1,    
                "text": "Node 1",    
                "state": "closed" //在最低子节点。不要设置它的state属性。设置也只能设置为Open。否则，会无线循环他本身的数据结构。
            }
    
    2.后台
        数据库也为树形结构，每条记录通过parent_id和isParent来存储
     
````

#### 三、图片的上传与访问

##### 6.图片保存的位置
````
    1、在传统的web项目中，直接将图片放在与WEB-INF同级的目录中，可以在jsp页面中直接访问图片。
    2、若并发量增加，可以添加服务器，做tomcat集群
        集群环境存在的问题：肯定会出现有时能访问到，有时不能访问到的情况
            （集群中两个Tomcat的文件不同步，用户上传图片到ATomcat，然后负载均衡服务器选择BTomcat查看，就会查看不到）
        解决方法：单独为图片创立一个提供http服务的服务器（现在的流程，上传图片，tomcat上传到HTTP服务器，查看图片直接从图片服务器查找）
                1、需要一个http服务器，可以使用Apache、Nginx（负载均衡和反向代理）。
                2、使用ftp服务上传图片，用Linux自带的ftp服务器:vsftbd。
````

##### 7.Nginx命令
````
    nginx start 启动Nginx
    nginx -s stop 强制关闭 
    nginx -s quit 安全关闭 
    nginx -s reload 改变配置文件的时候，重启nginx工作进程，来使配置文件生效
````

   [Nginx conf文件配置参数](http://www.nginx.cn/76.html)
   
##### 8.本地电脑搭建ftp服务
````
    1、下载软件FileZilla
    2、调用系统自带服务创建ftp服务
    3、添加ftp站点（注意使用本机IP）
    4、使用java代码访问ftp服务（使用Apache提供的Common-net jar包）
````

   [win10本地ftp服务器搭建](http://blog.csdn.net/gongpeng1966/article/details/70236529)
   
##### 9.富文本编辑器的使用
````
    1、导入js：kindeditor-all-min.js
    2、定义多行文本textarea（不可见、给定name）
    3、创建富文本编辑器
      itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
    4、同步文本框中的商品描述，即将编辑器中的内容放入textarea中
      itemAddEditor.sync();
````

#### 四、商品规格参数的实现

##### 10.商品规格参数的创建
   ###### 10.1第一种方案
```` 
    第一种方案：使用二维表来维护规格数据。
          表一：规格组信息 tb_item_param_group
              列名           类型         长度        可以null    键     说明
              Id             Int                      否          P      主键（自增长）
              group_name     varchar      20          否                 规格分组名称
              item_cat_id    Int                      否          F      商品分类id（外键）
          
          表二：规格项信息 tb_item_param_key
              列名           类型         长度        可以null    键     说明
              Id             Int          否                      P      主键（自增长）
              param_name     varchar      20          否                 规格项目名称
              group_id       Int          否                      F      规格分组id（外键）
          
          表三：商品规格信息 tb_item_param_value
              列名           类型         长度        可以null    键     说明
              item_id        Int                      否          P      商品id（联合主键）
              param_id       varchar                  否          P      规格项id（联合主键）
              param_value    varchar      500         否                 规格信息
          
          select pg.group_name,pk.param_name,pv.param_value from 
            tb_item_param_value pv,
            tb_item_param_key pk,
            tb_item_param_group pg 
          where item_id = 855739 and pv.param_id = pk.id and pg.id = pk.group_id;
          
          //使用外连接查询则全部外连接
          select pg.group_name,pk.param_name,pv.param_value from 
            tb_item_param_value pv 
            left join tb_item_param_key pk on pv.param_id = pk.id 
            left join tb_item_param_group pg on pg.id = pk.group_id
          where item_id = 855739
          
          缺点：1、需要创建的表比较多，表和表之间的关系复杂，查询时需要大量的关联。查询效率低。
               2、如果展示的规格组或者是规格项需要调整实现麻烦，需要添加排序列。
               3、维护不方便，如果删除某个规格分组信息，则所有与之相关的商品的规格信息都发生变化。

````
   ###### 10.2第二种方案
```` 
    第二种方案:模板方法解决
        1、每一个商品分类对应一个参数模板
            [
                {
                    "group": "主体",  //组名称
                    "params": [ // 记录规格成员
                        "品牌",
                        "型号",
                        "颜色",
                        "上市年份",
                        "上市月份"
                    ]
                }，
                {
                    "group": "网络",  //组名称
                    "params": [ // 记录规格成员
                        "4G",
                        "3G,
                        "2G"
                    ]
                }
            ]
        2、添加商品信息时，根据规格参数模板生成规格参数录入项。在页面上就是一个html片段。保存商品时将该片段（规格参数）生成
           一个字符串(json字符串)保存到数据库中。由于json字符串是大文本类型，所以使用mapper查询时要使用selectByExampleWithBLOBs方法
            [
                {
                    "group": "主体",
                    "params": [
                        {
                            "k": "品牌",
                            "v": "苹果（Apple）"
                        },
                        {
                            "k": "型号",
                            "v": "iPhone 6 A1589"
                        },
                        {
                            "k": "智能机",
                            "v": "是 "
                        }
                    ]
                }
            ]

        3、展示商品详情时，从数据库中取出规格参数信息，转换成html展示给用户。

````

#### 五、前台环境搭建及功能实现

##### 11.系统前台搭建
````
    前台系统和后台系统是分开的，只在数据库层面有关系，都是同一个数据库。
    
    为了系统的可扩展性
        将后台提供的服务直接抽取出来 服务层jitB2C-rest供其他系统调用（门户jitB2C-portal，移动端）
    jitb2c-manager(后台管理)   jitb2c-portal(门户)     （Android）      （IOS）
              |                     |                     |             |
              |                     |_____________________|_____________|
              |                              |
              |                         jitb2c-rest（服务层）      
              |______________________________|
                     |
                   mysql（数据库）
              
    优点：1.前台系统和服务层可以分开，降低系统的耦合度
         2.开发团队可以分开，提高开发效率
         3.系统分开可以灵活地进行分布式部署
    缺点：1.服务之间通信使用接口通信，开发工作量提高。
    
    前台系统分为两部分：1.服务层web工程，功能就是发布服务。jitb2c-rest
                    2.表现层，展示页面，没有业务逻辑，所有业务逻辑就是调用服务层的服务。jitb2c-portal
        
    jitb2c-rest 和 jitb2c-portal 完全独立，只存在调用服务的关系
    ①：服务层jitb2c-rest的搭建
        使用的技术
            1、mybatis
            2、spring
            3、springMVC(发布服务) 
        服务层工程搭建SSM框架整合参照jitb2c-manager,tomcat:8082
    
    ②：门户jitb2c-portal的搭建
        使用的技术
            1、jstl,jquery等
            2、spring
            3、springMVC(发布服务) 
            4、httpClient(使用Java代码模拟浏览器调用服务)
        服务层工程搭建SSM框架整合参照jitb2c-manager,tomcat:8082

````

##### 12.商品前台分类展示功能（jitb2c-portal）
````
    需求：首页左侧有一个商品分类。当鼠标放在商品分类上时，会展示出此分类下的子分类。
        当鼠标滑动到链接上时，会触发MouseMove事件。页面做一个ajax请求，请求json数据包含分类信息，得到json数据
        后初始化分类菜单，展示。
    
    portal的数据需要通过rest提供服务来获得
    
    两种方案：
    ①、浏览器 (ajax)--> portal --> rest
    ②、浏览器 (ajax)--> rest
    使用第二种方案，简洁，直接，省去一步http调用
````

##### 13.ajax跨域请求
````
    Js不能跨域请求，即8081访问8082（处于安全考虑）
    
    1、什么是跨域：
        1、域名不同时
        2、域名相同，端口号不同
        
    2、如何解决跨域请求？
        使用jsonp解决
    
    3、什么是jsonp?
        jsonp其实就是一种跨域解决方案。Js跨域请求数据是不可以的，但是Js跨域请求Js脚本是可以的。
    可以把数据封装成一个js语句，做一个方法的调用，跨域请求js脚本可以得到此脚本。得到Js脚本之后会
    立即执行，可以把数据作为参数传递到方法中。就可以获得数据，从而解决跨域问题。
        原理：浏览器在js请求中，是允许通过script标签的src跨域请求，可以在请求的结果中添加回调
    方法名，在请求页面中定义方法，既可获取到跨域请求的数据。
    
    jitb2c-portal 的 Controller
       接收页面传递过来的参数，参数就是方法的名称。返回一个json数据，需要把json数据包装成一句js代码
    返回一个字符串
    
    参数：回调方法名称
    返回值：字符串
        
````

#### 六、CMS系统的实现（内容管理系统）
##### 14.内容分类管理
````
    首页展示图片的共性：
        a)	链接
        b)	图片
        c)	标题
        d)	子标题
        e)  图片提示
        f)  价格
    
    将首页内容进行分类，分类应该是一个树形结构。
    
    先实现内容的分类管理，再实现内容管理。    
````

##### 15.首页大广告位
```` 
    1.方案选择
        方案一：（像首页的展示分类一样，portal发出请求，rest返回jsonp供浏览器调用）
            需要当首页加载完毕，首页大广告位就应该显示，但是没有一个触发的事件，所以不是太合适。
                优点：不需要二次请求，只需要页面加载内容数据即可，减少了portal的压力。
                缺点：①需要延迟加载。②不利于SEO优化。
        
        方案二：浏览器请求portal，portal请求rest，rest返回数据给portal，portal将数据封装成model或者request，
               供jsp调用
            优点：①有利于SEO优化②在portal可以对从rest得到的数据进行加工
            缺点：系统直接调用服务查询内容信息，多了一次http请求
    
    2.实现原理
        系统之间服务的调用，需要使用httpClient来实现。（因为portal和rest在同一个局域网内，速度很快，调用时可以
        忽略不计）
   
    3.内容服务发布（系统之间服务调用必然涉及到服务发布）
        服务是一个restful形式的服务，使用HTTP协议传递json格式的数据
        发布服务。接收查询参数。Restful风格内容分类id应该从url中取。例如:/rest/content/list/{contentCate
        goryId}(自己定义的)从url中取内容分类id，调用Service查询内容列表。返回内容列表。返回一个json格式的数
        据。可以使用TaotaoResult包装此列表。

    4.HttpClient的使用（使用java代码来调用http请求）
        1、使用HTTPclient执行get请求
             public void doGet() throws Exception{
                    //创建一个HttpClient对象
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    //创建一个Get对象
                    HttpGet get = new HttpGet("http://localhost:8082/rest/content/list/89");
                    //做请求
                    CloseableHttpResponse response = httpClient.execute(get);
                    //取响应的结果
                    int statusCode = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    String entity1 = EntityUtils.toString(entity,"utf-8");
                    System.out.println(statusCode+" "+entity1);
                    //关闭httpClient
                    response.close();
                    httpClient.close();
             }
             
             带参数的get请求
                    //创建一个Get对象
                    URIBuilder uriBuilder = new URIBuilder("http://www.sougou.com/web");
                    //用URIBuilder来封装uri
                    uriBuilder.addParameter("query","欢乐颂2");
                    HttpGet get = new HttpGet(uriBuilder.build());
                
        2、使用HTTPclient执行post请求
            public void doPost() throws Exception{
                    //创建一个HttpClient对象
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    //创建一个post请求 (只有*.html的才会被拦截，项目设置)
                    HttpPost post = new HttpPost("http://localhost:8083/httpclient/post.html");
                    //执行post请求
                    CloseableHttpResponse response = httpClient.execute(post);
                    String entity = EntityUtils.toString(response.getEntity());
                    System.out.println(entity);
            
                    response.close();
                    httpClient.close();
            }
            
            带参数的post请求
            //创建一个post请求 (只有*.html的才会被拦截，项目设置)
            HttpPost post = new HttpPost("http://localhost:8083/httpclient/post1.html");
    
            //创建一个entity，模拟一个表单
            List<NameValuePair> kvList = new ArrayList<>();
            kvList.add(new BasicNameValuePair("username","张三"));
            kvList.add(new BasicNameValuePair("password","123"));
    
            //包装成一个entity对象
            StringEntity entity = new UrlEncodedFormEntity(kvList);
            //设置请求的内容
            post.setEntity(entity);
            
        3、因为其他工程也可能用到HttpClient，所以将其放在common中
            详情见com/jitb2c/common/utils/HttpClientUtil.java
````

##### 15.首页大广告位的实现
````
    1、需求分析
        需要从服务层获取如下格式的字符串
        {
        　　　　"srcB":"http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
        　　　　"height":240,
        　　　　"alt":"",
        　　　　"width":670,
        　　　　"src":"http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
        　　　　"widthB":550,
        　　　　"href":"http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
        　　　　"heightB":240
        }
    2、当首页一存在时，就要获得该字符串，所以应该使用springMVC的modelAndView对象将字符串传递给jsp页面
      如何获得json字符串：通过调用rest服务层获得一个广告位对应的内容列表，将列表转换成json数据格式要求的pojo对象列表。
      需要使用httpclient调用服务
      
    实现流程：调用httpclient的工具类，传入url从rest服务层得到数据（String），将数据转化为在rest服务层对应的对象，
            将对象转化为前台所需要的数据格式的json数据，将json数据传入controller，controller将json数据转化为
            model对象给jsp页面展现。
````

