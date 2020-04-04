# LearnMybatis-day4-Mybatis-使用注解开发复杂查询

学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](


## @Results和@Result
- @Results和@Result注解可以解决数据库表与实体类名不对应的问题
- @ResultMap(value = {"userMap"})注解可以引用定义在其他地方的@Results和@Result关系
    - `value`是字段名可以省略不写，若数组中只有一个元素，大括号也可以省略
    - 简化版：  @ResultMap("userMap")

## 注解一对一
- @One 注解（一对一）：代替了<assocation>标签，是多表查询的关键，在注解中用来指定子查询返回单一对象。
- @One 注解属性介绍：
    - select：指定用来多表查询的 sqlmapper
    - fetchType 会覆盖全局的配置参数 lazyLoadingEnabled。。
- 使用格式：@Result(column=" ",property="",one=@One(select=""))
## 注解多对一
- @Many 注解（多对一）：代替了<Collection>标签,是是多表查询的关键，在注解中用来指定子查询返回对象集合。
- 注意：聚集元素用来处理“一对多”的关系。需要指定映射的 Java 实体类的属性，属性的 javaType（一般为 ArrayList）但是注解中可以不定义；
- 使用格式：@Result(property="",column="",many=@Many(select=""))

## 基于注解的Mybatis二级缓存的配置
- 一级缓存不用配置也不用开启，默认启用
- 二级缓存
