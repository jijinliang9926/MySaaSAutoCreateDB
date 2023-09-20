SaaS架构自动创建数据库操作，视频地址：https://www.bilibili.com/video/BV1r24y117s4/

本项目简单实现了通过执行sql语句自动化建库建表，并且添加到多数据源，并把多数据源名称添加到请求头中。

1.自动建库 2.创建数据源 3.把数据源添加到多数据源 4.把多数据源的名字添加到response请求头

crud执行的操作都来自请求头中的数据源。

（一）先自动建库<br>
    Post请求路径 Localhost:8080/create/initDB  携带表单参数 schemaName、ip、port、username、password

    1. 自动执行数据库初始化脚本 (有测试方法)
        Controller中的initDB方法就是初始化数据库，根据前端传入的数据库地址、数据库名字等等建库

    2. 根据用户名登录，进行动态数据库切换，（配置多数据源）
        Controller中的addDataSource方法就是动态添加数据源，根据客户写的数据源添加
        
（二）测试查询<br>
    Post请求路径 Localhost:8080/test/add  携带表单参数 name、company、addr、phone。查看新创建的test_saas库有没有添加成功<br>
    Get请求 Localhost:8080/test/find/1   查看是否能查到刚才添加的数据
    

---------------------------------------------------------------------------------
实战：
用户点击创建数据库，那么就应该执行两个操作

    1. 在用户的数据库中建库建表
    2. 在系统中添加用户的数据源，并把数据源记录下来，例如master、slave_01这种，哪个客户就取哪个数据源，用客户id或者用户名记录比较好
        (1) @DS("user01") 指定数据源的时候可以写固定值
        (2) @DS("#session.ds") 指定数据源的从session中key为ds对应的值
        (3) @DS("#header") 可以从请求头中找
        (4) Spel表达式

    个人理解第三种比较好，
        第一种是固定值耦合度高，
        第二种如果是分布式每个服务器的session都不一样，
        第三种每次请求都可以携带，
        第四种不懂。


这样用户就能访问自己的数据