# 项目名：transport_recordManage_system
### 简介：

### 1.该项目以JavaSE为基础，使用面向对象编程。

### 2.使用swing展示界面数据。

#### 2.1登录页面自定义实现一个JLabel作为验证码在登陆的时候进行验证码登录以及对用户名和密码的输入进行了智能提示。

![image-20201022234816971](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022234816971.png)

#### 2.2主页面可以支持修改个人信息实时监控密码框的文本变化

![image-20201022235057020](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235057020.png)

![image-20201022235118484](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235118484.png)

![image-20201023093536510](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093536510.png)

![image-20201023093555365](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093555365.png)

#### 2.3保存数据到本地时提供友好的保存文件框给用户选择保存的路径

![image-20201023093800263](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093800263.png)

#### 2.4采集数据

![image-20201022235312922](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235312922.png)

![image-20201022235421583](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235421583.png)

![image-20201022235403770](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235403770.png)

#### 2.5匹配数据

#### 2.6保存数据

#### 2.7发送数据

#### 2.8展示数据

![image-20201022235458410](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235458410.png)

![image-20201022235600374](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201022235600374.png)

### 3.MySQL8.0作为数据库，druid1.2.1作为数据库连接池。

#### 3.1在使用Java的JDBC连接数据库过程中，自定义和封装了访问数据库实现增删查改的的DbUtil.java工具类。

#### 3.2使用Druid数据库连接池连接数据库

![image-20201023093405866](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093405866.png)

### 4.使用Java的网络编程实现简单的客户端和服务器进行交互和数据传送。

使用客户端在数据采集完成，然后进行匹配好后，首先保存到数据到本地，然后将数据发送到服务器器中，再在服务器中将数据保存到数据库中。采用多线程将展示的数据会每隔三十秒自动刷新一次。

### 5使用工厂模式生成实体类或者实现类，大量使用配置文件进行配置对应实体类和数据库的表名，降低代码的耦合性。

![image-20201023000333972](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023000333972.png)

![image-20201023094020397](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023094020397.png)

![image-20201023094056730](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023094056730.png)      



![image-20201023000424105](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023000424105.png)

### 6.使用AES加密算法给用户的密码进行加密解密

![image-20201023093201571](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093201571.png)

![image-20201023093212173](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093212173.png)

![image-20201023093221621](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201023093221621.png)

