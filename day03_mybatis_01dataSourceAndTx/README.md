# LearnMybatis-day3-Mybatis连接池和事务

学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)



## mybatis中的连接池

\- mybatis连接池提供了3种方式的配置：

​    \- 在 Mybatis 中我们将它的数据源 dataSource 分为以下几类：

​        \- ![wzUl82H](https://i.imgur.com/wzUl82H.png =400x)

​    \- 配置的位置：主配置文件`SqlMapConfig.xml`中的`dataSource`标签，type属性就是表示采用何种连接池方式。

​    \- type属性的取值：

​		- POOLED: 采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现

​		- UNPOOLED: 采用传统的获取连接的方式，虽然也实现Javax.sql.DataSource接口，但是并没有使用池的思想。

​		- JNDI: 采用服务器提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到DataSource是不一样。

​	     - 注意：如果不是web或者maven的war工程，是不能使用的。 

\> 我们课程中使用的是tomcat服务器，采用连接池就是dbcp连接池。



##  POOLED vs UNPOOLED

![JuOX2Wf](https://i.imgur.com/JuOX2Wf.jpg)

\- UNPOOLED：每次注册一个新的连接

\- POOLED：`popConnection`



## 事务

\- 经典面试问题：

​    \- 什么是事务？

​    \- 事务的四大特性ACID

​    \- 不考虑隔离性会产生的问题？解决方法？——四种隔离级别

\- 设置自动提交事务：创建sqlSession时使用参数,`factory.openSession(true)`

​    \- 业务中不建议使用，同一个业务流程中多次commit会导致事务出问题










