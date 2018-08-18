package pers.jarome.redis.wclient.app.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.jarome.redis.wclient.app.properties.SysDbProperties;
import pers.jarome.redis.wclient.app.properties.SysProperties;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.common.system.util.EnvironmentUtils;
import pers.jarome.redis.wclient.core.biz.sys.service.DbsysService;

import java.io.File;

/**
 * 检测db文件是否存在，并创建表
 *
 * @author jarome
 * @date 2017/12/22
 */
@Component
@Order(1)
public class RcDbRunner implements CommandLineRunner {

    @Autowired
    @Qualifier("dbsysService")
    private DbsysService dbsysService;
    @Autowired
    private SysDbProperties sysDbProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(RcDbRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("RcDbRunner running");
        //获取环境变量
        String rcHomeEnv = EnvironmentUtils.getEnv(SystemConstants.REDIS_WCLIENT_HOME);
        String dbFileName = rcHomeEnv + "/" + sysDbProperties.getFilePath();
        File file = new File(dbFileName);
        if (!file.exists()) {
            //db文件不存在，创建
            file.createNewFile();
            LOGGER.info("创建文件：" + dbFileName);
        }
        dbsysService.initDb();
    }
}
