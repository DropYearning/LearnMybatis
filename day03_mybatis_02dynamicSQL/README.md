# LearnMybatis-day3-Mybatis-动态SQL语句

学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)

## 动态SQL语句
- 标签的使用：
    - if：条件判断。sql语句后跟where 1=1 : `select * from user where 1=1`
    - where：可以省略where 1=1。
    - foreach: 
        - 需求：`select * from user where id in(41,42,43)`
        - 适用于子查询的需求
- 抽取重复的SQL语句
    - 声明: `<sql id="defaultUser">
                   select * from user;
               </sql>
           `
    - 引用：`<include refid="defaultUser"></include>`
    
> 注意用于拼接的SQL语句末尾不能有分号；不拼接的SQL语句中的分号可加可不加








