1. Maven引入以下依赖。
    <groupId>org.lcm</groupId>
    <artifactId>exception</artifactId>
    <version>0.0.1-SNAPSHOT</version>

2. 无代码侵入。不需要在代码中加入注解。

3. 该公共代码库包含了Spring Security的exception。当你的工程引入该代码库的时候，会需要引入Spring Security依赖。
如果工程中不需要Spring Security 依赖，可以配置Spring Security放行。