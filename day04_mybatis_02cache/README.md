# LearnMybatis-day4-Mybatis-缓存

学习Mybatis框架

[Mybatis教程IDEA版-4天-2018黑马SSM-01_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili](

## Mybatis中的缓存
- 什么是缓存：存在于内存中的临时数据。
- 为什么使用缓存：减少和数据库的交互次数，提高执行效率。
- 什么样的数据能使用缓存，什么样的数据不能使用
    - 适用于缓存：
        - 经常查询并且不经常改变的。
        - 数据的正确与否对最终结果影响不大的。【缓存中的数据可能和数据库不同步】
    - 不适用于缓存：
        - 经常改变的数据【缓存了没有意义】
        - 数据的正确与否对最终结果影响很大的。
        - 例如：商品的库存，银行的汇率，股市的牌价。

### Mybatis中的一级缓存
- ** **一级缓存：它指的是Mybatis中SqlSession对象的缓存**。**
    - 当我们执行查询之后，查询的结果会同时存入到SqlSession为我们提供一块区域中。
    - 该区域的结构是一个Map。当我们再次查询同样的数据，mybatis会先去sqlsession中查询是否有，有的话直接拿出来用。
    - 当SqlSession对象消失时，mybatis的一级缓存也就消失了。
    - - `session.clearCache();`方法可以清空缓存
- ![x4EDzui](https://i.imgur.com/x4EDzui.png)
- ![vgVuFWK](https://i.imgur.com/vgVuFWK.png)
- 如果一级缓存与数据库不一致了，Mybatis如何实现数据同步？
    - 一级缓存是 SqlSession 范围的缓存，当调用SqlSession的**修改，添加，删除，commit()，close()等方法**时，就会清空一级缓存。 
    - ![amMD2QV](https://i.imgur.com/amMD2QV.png)

### Mybatis中的二级缓存
- ![4cHOLSh](https://i.imgur.com/4cHOLSh.png)
- **二级缓存:它指的是Mybatis中SqlSessionFactory对象的缓存**。
    - 由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
- **二级缓存中存放的数据不是对象**，读取**二级缓存时其实是用数据重新创建了一个对象**，并不是之前缓存的那个对象
- **sqlSession必须commit()或者close()之后才会将数据保存到二级缓存中**
- ![vUXLRnf](https://i.imgur.com/vUXLRnf.png)
- 二级缓存的使用步骤：
    - 1、让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置Settings）
    - 2、在当前的映射文件支持二级缓存（IUserDao.xml中添加<cache/>标签）
    - 3、让当前的操作支持二级缓存(在select标签中配置)


