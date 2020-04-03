# LearnMybatis day1-Mybati s-使用注解的mybatis
学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)



## 

## 使用注解配置

把IUserDao.xml移除，在dao接口的方法上使用@Select注解，并且指定SQL语句.

同时需要在SqlMapConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名。



明确：**我们在实际开发中，都是越简便越好，所以都是采用不写dao实现类的方式**。不管使用XML还是注解配置。但是Mybatis它是支持写dao实现类的。