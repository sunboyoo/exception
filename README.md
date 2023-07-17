
1. Import the following Maven dependency:
    
    <groupId>org.lcm</groupId>
    <artifactId>exception</artifactId>
    <version>0.0.1-SNAPSHOT</version>

2. Non-invasive to your code. There is no need to add annotations in your code.

3. This public code library includes Spring Security's exceptions.
When your project imports this code library, it will automatically bring in the Spring Security dependency and it will start working.
If your project doesn't need the Spring Security dependency, you can annotate your SpringApplication class with @SpringSecurityPermitAllRequests to allow all requests.

中文

1. Maven引入以下依赖。
    <groupId>org.lcm</groupId>
    <artifactId>exception</artifactId>
    <version>0.0.1-SNAPSHOT</version>

2. 无代码侵入。不需要在代码中加入注解。

3. 该公共代码库包含了Spring Security的exception。 
当你的工程引入该代码库的时候，会自动引入Spring Security依赖，并且工作。
如果你的工程中不需要Spring Security 依赖，可以在SpringApplication类上加注解@SpringSecurityPermitAllRequests放行所有请求。