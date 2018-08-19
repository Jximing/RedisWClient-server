package pers.jarome.redis.wclient.rs.core.sys.api;

import org.bouncycastle.jce.provider.symmetric.ARC4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pers.jarome.redis.wclient.rs.util.KaptchaUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * AssistController
 * @description 辅助类接口
 * @author jiangliuhong
 * @date 2018/8/17 11:20
 */
@Controller
public class AssistApi extends ARC4.Base {

    private static Logger logger = LoggerFactory.getLogger(AssistApi.class);

    /**
     * 验证码接口
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/cha")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            byte[] captchaChallengeAsJpeg = KaptchaUtils.verificationImg(request);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            assert captchaChallengeAsJpeg != null;
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (IOException e) {
            logger.error("kaptcha error", e);
        }
    }

}
