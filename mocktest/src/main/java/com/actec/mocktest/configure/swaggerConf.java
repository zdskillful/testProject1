package com.actec.mocktest.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zd
 * @Date 2019/8/2 14:42
 */

//注解标示该类为配置类,@Configuation注解包含了@Component注解
@Configuration
//注解开启 swagger2 功能
@EnableSwagger2
//@EnableWebMvc
public class swaggerConf implements WebMvcConfigurer{


    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .enable(true) // 是否开启
                .groupName("mock")
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(false) //
                .pathMapping("/").select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.actec.mocktest.modules.mock.controller")) // 指定扫描的包路径
                .paths(PathSelectors.any())// 指定路径处理PathSelectors.any()代表所有的路径
                .build().pathMapping("/");// 创建
    }


    /**
     * 构建 api文档的详细信息方法
     * 这里配置的信息都会显示在页面上
     * @return
     */
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("项目Rest Api")//页面标题
                .description("mocktest后台API文档1")//文档描述
                .contact(new Contact("ZhangD","http://192.168.66.106","824714586@qq.com"))//创建人信息
                .version("2.0")//版本
                .build();
    }

    @Bean
    public Docket createRestApi2(){

        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo2()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .enable(true) // 是否开启
                .groupName("admin")
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(false) //
                .pathMapping("/").select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.actec.mocktest.modules.admin.controller")) // 指定扫描的包路径
                .paths(PathSelectors.any())// 指定路径处理PathSelectors.any()代表所有的路径
                .build().pathMapping("/");// 创建
    }


    /**
     * 构建 api文档的详细信息方法
     * 这里配置的信息都会显示在页面上
     * @return
     */
    public ApiInfo apiInfo2(){
        return new ApiInfoBuilder()
                .title("项目Rest Api")//页面标题
                .description("mocktest后台API文档2")//文档描述
                .contact(new Contact("ZhangD","http://192.168.66.106","824714586@qq.com"))//创建人信息
                .version("2.0")//版本
                .build();
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}
