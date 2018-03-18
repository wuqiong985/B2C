# B2C
B2C毕设项目（Idea 创建）  

[MarkDown常用简单语法](https://www.jianshu.com/p/82e730892d42)

#### 相关笔记


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