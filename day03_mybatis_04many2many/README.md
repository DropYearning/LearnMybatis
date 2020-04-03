# LearnMybatis-day3-Myabatis多表查询之多对多

学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)

## 多表查询
- 表和表之间的关系
    - 一对多：一个用户可以下多个订单
    - 多对一：多个订单可以属于同一个人
        - 特例：如果拿出每一个订单，这个订单都只能属于一个用户。所以**Mabatis就把多对一看做一对一**
    - 一对一：一个人只能有一个身份证号，一个身份证号只能属于一个人
    - 多对多：一个学生可以被多个老师教，一个老师可以教多个学生

## Mabatis中的多表查询
- 实例：用户和账户
    - 一个用户可以有多个账户
    - 一个账户只能属于一个用户（多个账户也可以属于同一个用户）
- 步骤：
    - 1、建立两张表：用户表和账户表，让用户表和账户表之间具备一对多的挂你学，需要使用**外键**在账户表中添加【账户表以用户表id作为外键】
    - 2、建立两个实体类：用户实体类和账户实体类，让用户和账户的实体类能体现出来一对多的关系
    - 3、遍历两个配置文件（用户的配置文件和账户的配置文件）
    - 4、实现配置：当我们查询用户时，可以同时得到用户下所包含的账户信息；当我们查询账户时，可以同时得到账户所属用户的信息

## 一对一查询
- 查询所有账户，同时输出账户所属用户的所有信息（一对一查询）
- 一对一关系映射：从表(Account)实体应该包含一个主表(User)实体的对象引用
- `select u.*, a.id as aid, a.uid, a.money from account a, user u where u.id=a.uid;`

## 一对多查询
- 读取所有用户方法，同时获取到用户下所有账户的信息（一对多查询）
- 一对多关系映射：主表实体(User)应该包含从表实体的集合引用(List<Account>)
- SQL用左连接：`SELECT * from user u LEFT OUTER JOIN account a on u.id=a.UID;`
- Mabatis会自动封装右表数据组成的集合

## 多对多查询
- 用户的角色，一个用户可以有多个角色，一个角色可能属于多个用户
- 建立两张表：用户表[user]，角色表[role]。
    - 让用户表和角色表具有多对多的关系。
    - 需要使用中间表[user_role]，中间表中包含各自的主键，在中间表中是外键。
    - 
- 建立两个实体类：用户实体类和角色实体类
  	- 让用户和角色的实体类能体现出来多对多的关系 
  	- 各自包含对方一个集合引用    
- 建立两个配置文件
  	- 用户的配置文件
    - 角色的配置文件
- 实现配置：
    - 当我们查询用户时，可以同时得到用户所包含的角色信息
    - 当我们查询角色时，可以同时得到角色的所赋予的用户信息
- 从角色查询用户的多对多操作 : 
    - ![jaXPZLW](https://i.imgur.com/jaXPZLW.png)
    - ![TprsgFx](https://i.imgur.com/TprsgFx.png)
    - 需要2次左连接
    - SQL：`SELECT u.*, r.id as rid, r.role_name, r.role_desc from role r 
       LEFT JOIN user_role ur on r.id=ur.rid
       LEFT JOIN user u on u.id = ur.UID`
- 从用户到角色的多对多操作 
   - ![ONRuU7b](https://i.imgur.com/ONRuU7b.png)
   - SQL: `SELECT u.*, r.id as rid, r.role_name, r.role_desc from user u 
           LEFT JOIN user_role ur on u.id=ur.uid
           LEFT JOIN role r on r.id = ur.rid`
           