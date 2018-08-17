package pers.jarome.redis.wclient.app.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * DataSourceProperties
 *
 * @author jiangliuhong
 * @description 数据源配置
 * @date 2018/8/17 17:20
 */
@Configuration
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX_NAME)
public class DataSourceProperties {

    public static final String PREFIX_NAME = "spring.datasource.druid";

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceProperties.class);

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private int validationQueryTimeout;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;

    public DataSource createDataSource(){
        DruidDataSource datasource = new DruidDataSource();
        String rcHomeEnv = System.getenv(SystemConstants.REDIS_WCLIENT_HOME);
        String jdbcUrl = "jdbc:sqlite:" + rcHomeEnv + "/" + SystemConstants.DB_FILE_NAME;
        datasource.setUrl(jdbcUrl);
        datasource.setUrl(this.getUrl());
        datasource.setUsername(this.getUsername());
        datasource.setPassword(this.getPassword());
        datasource.setDriverClassName(this.getDriverClassName());
        //configuration
        datasource.setInitialSize(this.getInitialSize());
        datasource.setMinIdle(this.getMinIdle());
        datasource.setMaxActive(this.getMaxActive());
        datasource.setMaxWait(this.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(this.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(this.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(this.getValidationQuery());
        datasource.setTestWhileIdle(this.isTestWhileIdle());
        datasource.setTestOnBorrow(this.isTestOnBorrow());
        datasource.setTestOnReturn(this.isTestOnReturn());
        datasource.setPoolPreparedStatements(this.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setValidationQueryTimeout(this.getValidationQueryTimeout());
        datasource.setConnectionProperties(this.getConnectionProperties());
        try {
            datasource.setFilters(this.getFilters());
        } catch (SQLException e) {
            LOGGER.error("druid configuration initialization filter.", e);
        }
        return datasource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(int validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }
}