package pers.jarome.redis.wclient.app.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.jarome.redis.wclient.app.properties.SysDbProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * DruidWebStatFilter
 * @description Druid Monitor Filter
 * @author jiangliuhong
 * @date 2018/8/18 11:13
 */
@Configuration
@EnableConfigurationProperties(SysDbProperties.class)
public class DruidWebStatFilter {

    /**
     * DataSourceConfig
     * @description 配置一个管理后台的Servlet
     * @author jiangliuhong
     * @date 2018/8/17 16:50
     */
    @Bean
    public ServletRegistrationBean statViewServlet(SysDbProperties sysDbProperties){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>(4);
        //登录druid监控的账户
        initParams.put("loginUsername",sysDbProperties.getDruidMonitorUsername());
        //登录druid监控的密码
        initParams.put("loginPassword",sysDbProperties.getDruidMonitorPassword());

//        initParams.put("loginUsername","admin");
//        //登录druid监控的密码
//        initParams.put("loginPassword","admin");
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
