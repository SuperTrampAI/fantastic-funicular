package com.github.supertrampai.fantasticfunicular;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@SpringBootApplication
@ServletComponentScan
//@EntityScan(basePackageClasses = {User.class})
@ComponentScan(basePackages="com.github.supertrampai.fantasticfunicular.*")
@EnableSwagger2
public class FantasticFunicularApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FantasticFunicularApplication.class, args);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FantasticFunicularApplication.class);
    }
    // 配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
