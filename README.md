# 项目名：transport_recordManage_system
### 简介：

### 1.该项目以JavaSE为基础，使用面向对象编程。

### 2.使用swing展示界面数据。

#### 2.1登录页面自定义实现一个JLabel作为验证码在登陆的时候进行验证码登录以及对用户名和密码的输入进行了智能提示。

![image-20201022234816971](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-1.png)

#### 2.2主页面可以支持修改个人信息实时监控密码框的文本变化

![image-20201022235057020](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-2.png)

![image-20201022235118484](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-3.png)

![image-20201023093536510](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-4.png)

![image-20201023093555365](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-5.png)

#### 2.3保存数据到本地时提供友好的保存文件框给用户选择保存的路径

![image-20201023093800263](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-6.png)

#### 2.4采集数据

![image-20201022235312922](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-7.png)

![image-20201022235421583](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-8.png)

![image-20201022235403770](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-9.png)

#### 2.5匹配数据

#### 2.6保存数据

#### 2.7发送数据

#### 2.8展示数据

![image-20201022235458410](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-10.png)

![image-20201022235600374](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-11.png)

### 3.MySQL8.0作为数据库，druid1.2.1作为数据库连接池。

#### 3.1在使用Java的JDBC连接数据库过程中，自定义和封装了访问数据库实现增删查改的的DbUtil.java工具类。

#### 3.2使用Druid数据库连接池连接数据库

![image-20201023093405866](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-12.png)

### 4.使用Java的网络编程实现简单的客户端和服务器进行交互和数据传送。

使用客户端在数据采集完成，然后进行匹配好后，首先保存到数据到本地，然后将数据发送到服务器器中，再在服务器中将数据保存到数据库中。采用多线程将展示的数据会每隔三十秒自动刷新一次。

### 5使用工厂模式生成实体类或者实现类，大量使用配置文件进行配置对应实体类和数据库的表名，降低代码的耦合性。

![image-20201023000333972](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-13.png)

![image-20201023094020397](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-15.png)

![image-20201023094056730](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-14.png)      



![image-20201023000424105](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-16.png)

### 6.使用AES加密算法给用户的密码进行加密解密

![image-20201023093201571](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-17.png)

![image-20201023093212173](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-18.png)

![image-20201023093221621](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-19.png)

### 7.项目地址：

#### 7.1GitHub地址：https://github.com/LJF2402901363/transport_recordManage_system.git

#### 7.2gitee地址：https://gitee.com/ljf2402901363/transport_recordManage_system.git

## 8.运行项目

### 8.1本项目采用maven进行管理，首先从GitHub或者Gitee上拉取到本地：

```
git clone https://github.com/LJF2402901363/transport_recordManage_system.git
```

```
git clone https://gitee.com/ljf2402901363/transport_recordManage_system.git
```

建议使用gitee拉取项目，在Gitee上也可以看项目readme的图片，方便了解项目和运行项目。

### 8.2以maven项目在eclipse打开然后可以加载好后可以找到app包中的AppMain.java开始运行即可。

### 7.3以maven项目在idea中打开，运行时如果控制台出现“java: 非法字符: '\ufeff'”的提示，需要鼠标右键“TransportManageRecordSystem”项目，然后点击“Remove BOM”选项。

![image-20201023101845957](E:\JavaWorkSpace\workspace03\transport_recordManage_system\readMeImages\image-20.png)

这是因为在eclipse中的配置文件中是以UTF-8 with BOM来保存的，而idea默认的是UTF-8 with no BOM来保存。因此eclipse项目中的配置文件在idea中运行时需要 首先将项目 “remove BOM”才可以正常运行。