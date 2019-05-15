
package com.github.supertrampai.fantasticfunicular;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lxh95529@outlook.com
 * @version create time：2019年5月15日 下午1:54:17
 *  type description
 */


@Configuration
@EnableSwagger2
//@ComponentScan(basePackages ={"com.github.supertrampai.fantasticfunicular.action"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.supertrampai.fantasticfunicular"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("fantasticfunicular 文档")
                .description("Input Problem solving Output")
                .termsOfServiceUrl("https://supertrampai.github.io/")
                .version("1.0")
                .build();
    }
}
