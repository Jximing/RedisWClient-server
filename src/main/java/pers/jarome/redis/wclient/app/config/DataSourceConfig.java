package pers.jarome.redis.wclient.app.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jarome.redis.wclient.app.properties.DataSourceProperties;
import pers.jarome.redis.wclient.app.run.CheckInstallRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSourceConfig
 *
 * @author jiangliuhong
 * @description 数据源配置
 * @date 2018/8/17 16:31
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckInstallRunner.class);

    /**
     * DataSourceConfig
     * @description 配置一个管理后台的Servlet
     * @author jiangliuhong
     * @date 2018/8/17 16:50
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>(4);
        //登录druid监控的账户
        initParams.put("loginUsername","admin");
        //登录druid监控的密码
        initParams.put("loginPassword","admin");
        //默认就是允许所有访问
        initParams.put("allow","");
        //黑名单的IP
        //initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    
    /**
     * DataSourceConfig
     * @description 配置一个web监控的filter
     * @author jiangliuhong
     * @date 2018/8/17 16:50
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }


}
