package pers.jarome.redis.wclient.rs.core.sys.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.jarome.redis.wclient.app.properties.SysProperties;
import pers.jarome.redis.wclient.common.constant.CacheConstants;
import pers.jarome.redis.wclient.common.exception.NoUserException;
import pers.jarome.redis.wclient.common.exception.PasswordMissException;
import pers.jarome.redis.wclient.common.web.encrypt.anno.EncryptBody;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;
import pers.jarome.redis.wclient.core.biz.sys.domain.UserDO;
import pers.jarome.redis.wclient.core.biz.sys.service.CacheService;
import pers.jarome.redis.wclient.core.biz.sys.service.UserService;
import pers.jarome.redis.wclient.rs.common.api.BaseController;
import pers.jarome.redis.wclient.rs.common.constant.ApiPath;
import pers.jarome.redis.wclient.rs.core.sys.param.LoginParam;
import pers.jarome.redis.wclient.rs.core.sys.param.SysInitParam;
import pers.jarome.redis.wclient.rs.core.sys.vo.WclientConfigVO;
import pers.jarome.redis.wclient.rs.util.KaptchaUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * IndexController
 * @description 首页Api
 * @author jiangliuhong
 * @date 2018/8/15 21:12
 * @version 1.0.0
 */
@Controller
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

    
    /**
     * IndexApi
     * @description 系统配置
     * @author jiangliuhong
     * @date 2018/8/19 15:03
     * @version 1.0.0
     */
    @RequestMapping(value = ApiPath.SYS_CONFIG,method = RequestMethod.GET)
    @EncryptBody
    public ResultEntity<WclientConfigVO> config(){
        WclientConfigVO config = new WclientConfigVO();
        config.setInstall(cacheServie.getBoolean(CacheConstants.IS_INSTALL));
        return ResultEntity.successNoMessage(config);
    }

    
    /**
     * IndexApi
     * @description 初始化
     * @param sysInit
     * @author jiangliuhong
     * @date 2018/8/19 15:37
     * @version 1.0.0
     */
    @PostMapping(value = ApiPath.SYS_INIT)
    public ResultEntity<Object> init(@EncryptBody SysInitParam sysInit){
        if (StringUtils.isBlank(sysInit.getPassword()) || StringUtils.isBlank(sysInit.getRePassword())) {
            return failed("参数错误");
        }
        if (!StringUtils.equals(sysInit.getPassword(), sysInit.getRePassword())) {
            return failed("两次密码不一致");
        }
        UserDO user = userService.getByUsername(sysProperties.getAdmin());
        if (user != null) {
            return failed("已初始化，不能重复操作");
        }
        //添加admin账户
        userService.addUser(sysProperties.getAdmin(), sysInit.getPassword());
        //删除缓存
        cacheServie.del(CacheConstants.IS_INSTALL);
        return success("初始化成功");
    }

    
    /**
     * IndexApi
     * @description 登录
     * @param loginParam
     * @author jiangliuhong
     * @date 2018/8/19 15:41
     * @version 1.0.0
     */
    @PostMapping(value = ApiPath.SYS_LOGIN)
    public ResultEntity login(@EncryptBody LoginParam loginParam, HttpServletRequest request){
        if (StringUtils.isBlank(loginParam.getUsername()) || StringUtils.isBlank(loginParam.getPassword()) || StringUtils.isBlank(loginParam.getCode())) {
            return failed("参数错误");
        }
        try {
            //判断验证码
            if (!KaptchaUtils.verifyCode(request, loginParam.getCode())) {
                return failed("验证码错误");
            }
            //判断账号密码
            String token = userService.login(loginParam.getUsername(), loginParam.getPassword());
            return ResultEntity.successNoMessage(token);
        } catch (NoUserException nue) {
            return failed("账号不存在");
        } catch (PasswordMissException pme) {
            return failed("账号密码不匹配");
        }
    }
}
