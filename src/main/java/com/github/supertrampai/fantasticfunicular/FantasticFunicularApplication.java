package com.github.supertrampai.fantasticfunicular;

import com.github.pagehelper.PageHelper;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@SpringBootApplication
@ServletComponentScan
//@EntityScan(basePackageClasses = {User.class})
@ComponentScan(basePackages="com.github.supertrampai.fantasticfunicular.*")
//@MapperScan("com.github.supertrampai.fantasticfunicular.repository")//另外一种方式可以在每个mapper上面加注解@Mapper
@EnableSwagger2
@EnableCaching
/**
* @EnableScheduling 开启注解
* */
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

    /**
     * tomcatEmbedded 这段代码是为了解决，上传文件大于10M出现连接重置的问题。
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }


}
