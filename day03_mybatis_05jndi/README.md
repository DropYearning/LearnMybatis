# LearnMybatis-day3-Mabatis-JNDI数据源的使用

学习Mybatis框架
[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)

## JNDI数据源的使用
- JNDI(Java Naming and Directory Interface,Java命名和目录接口)是SUN公司提供的一种标准的Java命名系统接口，JNDI提供统一的客户端API，通过不同的访问提供者接口JNDI服务供应接口(SPI)的实现，由管理者将JNDI API映射为特定的命名服务和目录系统，使得Java应用程序可以和这些命名服务和目录服务之间进行交互。
- ![IqOtmKt](https://i.imgur.com/IqOtmKt.png)

> 本项目没有启动成功！Tomcat报错！

```
Connected to server
[2020-04-03 05:50:16,051] Artifact day03_mybatis_05jndi:war: Artifact is being deployed, please wait...
03-Apr-2020 17:50:16.693 警告 [RMI TCP Connection(2)-127.0.0.1] org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory.getObjectInstance Name = eesy_mybatis Property maxActive is not used in DBCP2, use maxTotal instead. maxTotal default value is 8. You have set value of "20" for "maxActive" property, which is being ignored.
03-Apr-2020 17:50:16.694 警告 [RMI TCP Connection(2)-127.0.0.1] org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory.getObjectInstance Name = eesy_mybatis Property maxWait is not used in DBCP2 , use maxWaitMillis instead. maxWaitMillis default value is -1. You have set value of "10000" for "maxWait" property, which is being ignored.
03-Apr-2020 17:50:16.738 信息 [RMI TCP Connection(2)-127.0.0.1] org.apache.jasper.servlet.TldScanner.scanJars 至少有一个JAR被扫描用于TLD但尚未包含TLD。 为此记录器启用调试日志记录，以获取已扫描但未在其中找到TLD的完整JAR列表。 在扫描期间跳过不需要的JAR可以缩短启动时间和JSP编译时间。
03-Apr-2020 17:50:16.741 严重 [RMI TCP Connection(2)-127.0.0.1] org.apache.catalina.core.StandardContext.startInternal One or more listeners failed to start. Full details will be found in the appropriate container log file
03-Apr-2020 17:50:16.750 严重 [RMI TCP Connection(2)-127.0.0.1] org.apache.catalina.core.StandardContext.startInternal Context [/day03_mybatis_05jndi] startup failed due to previous errors
[2020-04-03 05:50:16,767] Artifact day03_mybatis_05jndi:war: Error during artifact deployment. See server log for details.
03-Apr-2020 17:50:25.974 信息 [localhost-startStop-1] org.apache.catalina.startup.HostConfig.deployDirectory 把web 应用程序部署到目录 [/Users/brightzh/opt/apache-tomcat-8.5.53/webapps/manager]
03-Apr-2020 17:50:26.027 信息 [localhost-startStop-1] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/Users/brightzh/opt/apache-tomcat-8.5.53/webapps/manager] has finished in [54] ms

```


           