package pers.jarome.redis.wclient.app.component.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;
import pers.jarome.redis.wclient.common.exception.CustomizedRuntimeException;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.common.system.util.EnvironmentUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * CustomEnvironmentPostProcessor
 *
 * @author jiangliuhong
 * @version 1.0
 * @description 加载外部文件
 * @date 2018/8/12 0:24
 */
@Component
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final Log log;

    public CustomEnvironmentPostProcessor() {
        this.log = LogFactory.getLog(getClass());
    }

    /**
     * 配置文件存在文件夹
     */
    private static final String CONFIG_PATH = "/config/";
    private static final String SUBFIX_NAME = ".properties/.yml";
    private static final String SOURCE_NAME = "cus";

    private List<Properties> readProperty(String path) {
        List<Properties> list = new ArrayList<Properties>();
        File root = new File(path);
        try {
            if (root.exists() && root.isDirectory()) {
                File[] files = root.listFiles();
                if (files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory()) {
                            list.addAll(readProperty(file.getAbsolutePath()));
                        } else {
                            int i1 = file.getName().lastIndexOf(".");
                            if (i1 >= 0) {
                                String subfixName = file.getName().substring(i1);
                                if (SUBFIX_NAME.contains(subfixName)) {
                                    Properties properties = new Properties();
                                    properties.load(new FileInputStream(file));
                                    list.add(properties);
                                }
                            }
                        }
                    }
                }
            }
            return list;
        } catch (IOException e) {
            throw new CustomizedRuntimeException("Failed to load configuration file under environment variable!", e);
        }
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        log.info("Load the configuration file under the environment variable,starting.");
        //获取环境变量
        String rcHomeEnv = EnvironmentUtils.getEnv(SystemConstants.REDIS_WCLIENT_HOME);
        List<Properties> properties = readProperty(rcHomeEnv + CONFIG_PATH);
        if (properties.size() > 0) {
            //如果环境变量下存在配置文件，则移除springboot项目中的配置
            configurableEnvironment.getPropertySources().remove("applicationConfigurationProperties");
            for (int i = 0; i < properties.size(); i++) {
                PropertiesPropertySource propertySource = new PropertiesPropertySource(SOURCE_NAME+"_"+i, properties.get(i));
                configurableEnvironment.getPropertySources().addLast(propertySource);
            }
        }
        log.info("Load the configuration file under the environment variable,end.");
    }
}
