# B2C
B2C毕设项目（Idea 创建）  

[MarkDown常用简单语法](https://www.jianshu.com/p/82e730892d42)

#### 相关笔记


#####1.SKU:最小库存量单位  
````
      Stock Keeping Unit（库存量单位）
      对商品进行细粒度的划分  
      如 Iphone8 16G 深空灰 和 Iphone8 16G 银白色 就是两个不同的SKU
````
     
#####2.逆向工程 
````
      Mybatis的逆向工程。根据数据库表生成java代码。
      生成过程[Idea使用mybatis插件生成逆向工程](https://segmentfault.com/a/1190000009058867)
      注意，mybatisGenerator中的mybatis-generator-config_1_0.dtd文件获取不到，直接从网上下了一个，放在resource中，直接引用位置
      详情见“E:\淘淘商城\mybatisGenerator”目录下新增的Idea工程
````

######3.SSM框架整合
````
    4.1	整合的思路
    
    4.1.1	Dao层
        使用mybatis框架。创建SqlMapConfig.xml。
        创建一个applicationContext-dao.xml
        1、配置数据源
        2、需要让spring容器管理SqlsessionFactory，单例存在。
        3、把mapper的代理对象放到spring容器中。使用扫描包的方式加载mapper的代理对象。
    
    4.1.2	Service层
        1、事务管理
        2、需要把service实现类对象放到spring容器中管理。
    
    4.1.3	表现层
        1、配置注解驱动
        2、配置视图解析器
        3、需要扫描controller
    
    4.1.4	Web.xml
        1、spring容器的配置
        2、Springmvc前端控制器的配置
        3、Post乱码过滤器
````