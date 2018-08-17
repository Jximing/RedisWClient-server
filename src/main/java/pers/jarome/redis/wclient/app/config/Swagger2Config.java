package pers.jarome.redis.wclient.app.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2Config
 *
 * @author jiangliuhong
 * @version 1.0.0
 * @description swagger2配置
 * @date 2018/8/17 11:47
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static String version = "1.0.0";

    private static String basePackage = "pers.jarome.redis.wclient.rs.api";

    //    @Bean
//    public Docket sysRestApi() {
//        Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage(basePackage+".sys");
//        //ApiInfo apiInfo = new ApiInfoBuilder().title("系统服务-接口").description("系统本身提供的服务").version(version).build();
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("writ接口文档")
//                .description("文书交互接口文档")
//                .version("1.0")
//                .contact(new Contact("liufagui", "http://open.thunisoft.com/liufagui1/writ", "liufagui@thunisoft.com"))
//                .build();
//        return new Docket(DocumentationType.SWAGGER_2).groupName("SYS_API").apiInfo(apiInfo).select().apis(predicate).paths(PathSelectors.any()).build();
//    }map key sex '男'
    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("系统服务-接口")
                .description("系统本身提供的服务")
                .version(version)
                .contact(new Contact("jiangliuhong", "https://github.com/jiangliuhong/RedisWClient-web", "liufagui@thunisoft.com"))
                .build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .groupName("SYS_API")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
