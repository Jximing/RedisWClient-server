package pers.jarome.redis.wclient.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import pers.jarome.redis.wclient.app.properties.SysDbProperties;
import pers.jarome.redis.wclient.app.properties.SysProperties;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

/**
 * @author jarome
 * @date 2017/12/22
 **/
@Configuration
public class MybatisInitConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(MybatisInitConfig.class);

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private SysDbProperties sysDbProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        //注入url
        String rcHomeEnv = System.getenv(SystemConstants.REDIS_WCLIENT_HOME);
        String jdbcUrl = "jdbc:sqlite:" + rcHomeEnv + "/" +sysDbProperties.getFilePath();
        dataSourceProperties.setUrl(jdbcUrl);
        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resolver.getResources("classpath:/mapper/*.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
        } catch (FileNotFoundException ex) {
            LOGGER.warn("class path resource [mapper/] cannot be resolved to URL because it does not exist");
        }
        return sqlSessionFactoryBean.getObject();
    }


}
