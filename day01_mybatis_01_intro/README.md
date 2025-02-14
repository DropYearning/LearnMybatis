# LearnMybatis-day1-Mybatis-框架的环境搭建
学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)

## 

## Mybatis环境搭建

- 第一步：创建maven工程并导入坐标

- 第二步：创建实体类和dao的接口

- 第三步：创建Mybatis的主配置文件SqlMapConifg.xml

- 第四步：创建映射配置文件 IUserDao.xml

**注意：**

- 第一个：创建IUserDao.xml 和 IUserDao.java时名称是为了和我们之前的知识保持一致。在Mybatis中它把持久层的操作接口名称和映射文件也叫做：Mapper，**所以：IUserDao 和 IUserMapper是一样的**

- 第二个：在idea中创建目录的时候，它和包是不一样的包在创建时：com.itheima.dao它是三级结构目录在创建时：com.itheima.dao是一级目录

- 第三个：**mybatis的映射配置文件位置必须和dao接口的包结构相同**

- 第四个：**映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名**

- 第五个：映射配置文件的操作配置（select），id属性的取值必须是dao接口的方法名



> 当我们遵从了第三，四，五点之后，我们在开发中就无须再写dao的实现类。

## 使用XML配置

mybatis的入门案例（day01_eesy_mybatis）

​		第一步：读取配置文件

​		第二步：创建SqlSessionFactory工厂

​		第三步：创建SqlSession

​		第四步：创建Dao接口的代理对象

​		第五步：执行dao中的方法

​		第六步：释放资源

注意事项：

- 不要忘记在映射配置中告知mybatis要封装到哪个实体类中

- 配置的方式：指定实体类的全限定类名