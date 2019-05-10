package com.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉<br>
 * 〈Swagger2 配置类〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 10:13
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Configuration  //通过spring 来加载该配置类
@EnableSwagger2 //开启Swagger2
@ConditionalOnProperty(prefix = "swagger", value = {"enable"}, havingValue ="true")
public class Swagger2Config {

    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .groupName("moremaven")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                //过滤生成链接
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .license("Apache License Version 2.0")
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .contact(new Contact("tangzedong", "https://cnblogs.com",
                        "tangze.pro@qq.com"))
                .version("1.0")
                .build();

    }

}
