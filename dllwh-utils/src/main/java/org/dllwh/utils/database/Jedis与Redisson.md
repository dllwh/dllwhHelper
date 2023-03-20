Redis的两个框架：Jedis与Redisson

> 本文的主要内容为对比Redis的两个框架：Jedis与Redisson，两者拥有各自的优势与缺点


# Jedis

- Jedis是Redis的Java实现的客户端，其API提供了比较全面的Redis命令的支持；

- Jedis中的方法调用是比较底层的暴露的Redis的API，也即Jedis中的Java方法基本和Redis的API保持着一致，了解Redis的API，也就能熟练的使用Jedis。

- Jedis使用阻塞的I/O，且其方法调用都是同步的，程序流需要等到sockets处理完I/O才能执行，不支持异步。Jedis客户端实例不是线程安全的，所以需要通过连接池来使用Jedis。

- Jedis仅支持基本的数据类型如：String、Hash、List、Set、Sorted Set。


# Redisson

- Redisson是一个基于java编程框架netty进行扩展了的redis，并且实现了分布式和可扩展的Java数据结构，和Jedis相比，功能较为简单，不支持字符串操作，不支持排序、事务、管道、分区等Redis特性。Redisson的宗旨是促进使用者对Redis的关注分离，从而让使用者能够将精力更集中地放在处理业务逻辑上。

- Redisson使用非阻塞的I/O和基于Netty框架的事件驱动的通信层，其方法调用是异步的。Redisson的API是线程安全的，所以可以操作单个Redisson连接来完成各种操作。

- Redisson不仅提供了一系列的分布式Java常用对象，基本可以与Java的基本数据结构通用，还提供了许多分布式服务。