package com.example.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.config.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/4/30/0030 15:14
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Configuration
public class MyWebMvcConfigAdapter implements WebMvcConfigurer{

    /**
     * 格式化(Spring 默认并没有配置如何将字符串转为日期类型，
     * 为了支持可按照指定格式转为日期类型，需要添加一个DateFormatter 类)
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("----------格式化日期 开始----------");
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
        System.out.println("--------------格式化日期 结束----------------");

    }



    /**
     * 跨域访问配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        System.out.println("-----------配置跨域访问-------START--------");
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://127.0.0.1:8080/spring") //                .allowedOrigins("http://domain2.com")
//                .allowedMethods("POST","GET");
        System.out.println("------------配置跨域访问------END---------------");
    }


    /**
     * 增加静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        System.out.println("--------------增加静态资源的访问--------START-------");
        //自定义项目内目录
        //addResourceLocations() 指的是文件放置的目录   addResourceHandler() 指的是对外暴露的访问路径
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/admin/scripts",
                        "classpath:/templates/admin/images",
                        "classpath:/templates/admin/styles",
                        "classpath:/static/**");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

//        super.addResourceHandlers(registry);
        System.out.println("-----------增加静态资源的访问-----END--------------");
    }


    /**
     * URI到试图的映射
     *
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("设置 URI  到视图的映射-------START--------");

        //对于 login 请求，设置返回的视图为 login.vm
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/login").setViewName("login");


        //所有以 /**/*.do 结尾的请求重定向到／ index.html 请求
        registry.addRedirectViewController("/**/*", "login");

        //        super.addViewControllers(registry);
        System.out.println("设置 URI 到视图的映射-----------END----------");
    }




    /**
     * 拦截器 （注册自定义的拦截器）
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("开启拦截器----------START-----------------");
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin","/login","/assets/**","/js/**");
        //增加一个拦截器， 检查会话，URL 以admin开头的都使用此拦截器
//        registry.addInterceptor(new SessionHandlerinterceptor()).addPathPatterns("/admin/**");

        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/error","/login","test/**")
                .excludePathPatterns("/swagger-resource/**","/webjars/**", "/v2/**", "/swagger-ui.html/**");

        System.out.println("开启拦截器---------END-----------------");
    }



    /**
     * 配置fastJson
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

         /*
         * 1、需要先定义一个convert转换消息的对象
         * 2、添加fastJson的配置信息，比如：是否要格式化返回json数据
         * 3、在convert中添加配置信息
         * 4、将convert添加到converters当中
         *
         */
        System.out.println("处理中文乱码-----START-------");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();


        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
//        super.configureMessageConverters(converters);
        System.out.println("处理中文乱码------END----------");
    }



}
