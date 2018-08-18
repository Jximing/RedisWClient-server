package pers.jarome.redis.wclient.rs.api.sys;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jarome.redis.wclient.app.properties.SysProperties;
import pers.jarome.redis.wclient.common.constant.CacheConstants;
import pers.jarome.redis.wclient.common.exception.NoUserException;
import pers.jarome.redis.wclient.common.exception.PasswordMissException;
import pers.jarome.redis.wclient.common.system.constant.SystemConstants;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDo;
import pers.jarome.redis.wclient.core.biz.sys.service.CacheService;
import pers.jarome.redis.wclient.core.biz.sys.service.UserService;
import pers.jarome.redis.wclient.rs.api.BaseController;
import pers.jarome.redis.wclient.rs.common.constant.ApiPath;
import pers.jarome.redis.wclient.rs.util.KaptchaUtils;
import pers.jarome.redis.wclient.rs.vo.WclientConfigVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * IndexController
 * @description 首页Api
 * @author jiangliuhong
 * @date 2018/8/15 21:12
 * @version 1.0.0
 */
@RestController
public class IndexApi extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(IndexApi.class);

    @Autowired
    @Qualifier("mapCacheServie")
    private CacheService cacheServie;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    private SysProperties sysProperties;

    @GetMapping(value = ApiPath.CONFIG)
    public ResultEntity<WclientConfigVo> config(){
        WclientConfigVo config = new WclientConfigVo();
        config.setInstall(cacheServie.getBoolean(CacheConstants.IS_INSTALL));
        return ResultEntity.successNoMessage(config);
    }

    public ResultEntity<Object> initAdmin(String password, String rePassword){
        if (StringUtils.isBlank(password) || StringUtils.isBlank(rePassword)) {
            return failed("参数错误");
        }

        if (!StringUtils.equals(password, rePassword)) {
            return failed("两次密码不一致");
        }
        UserDo user = userService.getByUsername(sysProperties.getAdmin());
        if (user != null) {
            return failed("已初始化，不能重复操作");
        }
        //添加admin账户
        userService.addUser(sysProperties.getAdmin(), password);
        //删除缓存
        cacheServie.del(CacheConstants.IS_INSTALL);
        return success("初始化成功");
    }


    public ResultEntity login(String username, String password, String code, HttpServletRequest request){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            return failed("参数错误");
        }
        try {
            //判断验证码
            if (!KaptchaUtils.verifyCode(request, code)) {
                return failed("验证码错误");
            }
            //判断账号密码
            String token = userService.login(username, password);
            return ResultEntity.successNoMessage(token);
        } catch (NoUserException nue) {
            return failed("账号不存在");
        } catch (PasswordMissException pme) {
            return failed("账号密码不匹配");
        }
    }
}
