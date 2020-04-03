# LearnMybatis day1-Mybatis-自实现Dao接口
学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](https://www.bilibili.com/video/BV1Db411s7F5?from=search&seid=17279186468718936332)



## mybatis模糊查询

![kORgEfm](https://i.imgur.com/kORgEfm.jpg)

- `select * from user where username like '%${value}%'`的方式不推荐

- `select * from user where username like #{anyword}`  anyword = ""%王" 这样的方式更好





## 新增用户 id 的返回值

新增用户后，同时还要返回当前新增用户的 id 值，因为 id 是由数据库的自动增长来实现的，所以就相当于我们要在新增后将自动增长 auto_increment 的值返回。

![7HBZNvE](https://i.imgur.com/7HBZNvE.png)





## OGNL表达式

- Object Graphic Navigation Language 对象图导航语言 ： 通过对象中的取值方法来获取数据，在写法上把get省略了

- 比如：获取用户的名称，正常写法是`user.getUsername()`, OGNL表达式的写法是`user.username`

- mybatis中只用写username，而不用写user.username；因为在ParameterType中已经提供了所属的类，所以此时不需要写对象名



## POJO对象

- POJO（Plain Ordinary Java Object）简单的Java对象，实际就是普通JavaBeans

- 开发中通过 pojo 传递查询条件 ，查询条件是综合的查询条件，不仅包括用户查询条件还包括其它的查询条件（比如将用户购买商品信息也作为查询条件），这时可以使用包装对象传递输入参数。

- 需求：根据用户名查询用户信息，查询条件放到 QueryVo 的 user 属性中。



## 设置别名Alia

- 方法一：在SQL语句中使用别名`select id as userId, username as userName from user` 

​    \- as前面是数据表列名， as后面是实体类属性名

- 方法二：在XML中单独配置

​    \```

​        <!-- 配置 查询结果的列名和实体类的属性名的对应关系 -->

​        <resultMap id="userMap" type="uSeR">

​            <!-- 主键字段的对应 -->

​            <id property="userId" column="id"></id>

​            <!--非主键字段的对应-->

​            <result property="userName" column="username"></result>

​            <result property="userAddress" column="address"></result>

​            <result property="userSex" column="sex"></result>

​            <result property="userBirthday" column="birthday"></result>

​        </resultMap>

​    \```

​    - property对应Java中的属性名，严格区分大小写

​    - column对应数据库表列名，在Mysql中可以不区分大小写

​    - **使用了resultMap之后，resultType字段就可以删除了，替换为resultMap = "userMap" 即可 **

​    - **这种方式需要多解析一段XML，效率会变低**



## mybatis自定义Dao实现crud的分析

![UlbNHLc](https://i.imgur.com/UlbNHLc.jpg)



## mybatis代理dao执行过程分析

![6cIAl6g](https://i.imgur.com/6cIAl6g.jpg)



## SqlMapConfig中的实用标签



- `properties`标签，可以配置本XML文件内其他地方可以引用的变量的值。

- 内部使用property标签

- <property name="jdbc.driver" value="com.mysql.jdbc.Driver"/>

- <property name="driver" value="${jdbc.driver}"/>

- 后面使用${name}来引用

- `properties`中的property可以写在独立的`.properties`格式的文件中， 是按类路径的写法来写，并且必须存在于类路径下

​    \- <properties resource="jdbcConfig.properties"/>

- `typeAliases`设置类型的别名

- ![kLS7TL6](https://i.imgur.com/kLS7TL6.png =600x)

- 可以定义单独的别名

- 也可以利用<package>标签将该包之下的所有实体类都会注册别名，并且类名就是别名，不区分大小写

- `package`标签也可以注册指定包下的所有 mapper 接口，当指定了之后就不需要再写mapper以及resource或者class了

\```

<mappers>

​            <!--对应resources目录下的路径-->

<!--            <mapper resource="com/study/dao/IUserDao.xml"/>-->

​            <!--package标签用于指定dao接口所在包，当指定了之后就不需要再写mapper以及resource或者class了-->

​            <package name="com.study.dao"/>

​        </mappers>

\```