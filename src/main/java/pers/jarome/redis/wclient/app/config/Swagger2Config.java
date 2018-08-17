package pers.jarome.redis.wclient.app.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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

    private String version = "1.0.0";

    private String basePackage = "pers.jarome.redis.wclient.rs.api";

    @Bean
    public Docket sysRestApi() {
        Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage(basePackage+".sys");
        ApiInfo apiInfo = new ApiInfoBuilder().title("系统服务-接口").description("系统本身提供的服务").version(version).build();
        return new Docket(DocumentationType.SWAGGER_2).groupName("SYS_API").apiInfo(apiInfo).select().apis(predicate).paths(PathSelectors.any()).build();
    }
}
