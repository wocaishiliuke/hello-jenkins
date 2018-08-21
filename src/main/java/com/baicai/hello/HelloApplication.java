package com.baicai.hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @SpringBootApplication包含: @Configuration + @ComponentScan + @EnableAutoConfiguration
 * 其中@Configuration标记为配置类;@ComponentScan默认只扫描同包/子包;@EnableAutoConfiguration开启自动配置
 */
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.baicai.hello")//不在同包下的要指定包
//SpringBootServletInitializer类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似
public class HelloApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HelloApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    //检索app自动创建的或springboot自动添加的bean,排序并打印
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        final String[] beanNames = ctx.getBeanDefinitionNames();
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Let's inspect the beans provided by Spring Boot:");
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
    }
}
