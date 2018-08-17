package pers.jarome.redis.wclient.rs.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import pers.jarome.redis.wclient.core.biz.util.SpringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author jarome
 * @date 2017/12/28
 **/
public class KaptchaUtils {

    private static String KAPTCHA_SESSION_KEY = "kaptchacode";

    /**
     * 获取bean
     *
     * @return
     */
    private static DefaultKaptcha getKaptcha() {
        return SpringUtils.getBean(DefaultKaptcha.class);
    }

    /**
     * 获取验证码
     *
     * @param request
     * @return
     */
    public static byte[] verificationImg(HttpServletRequest request) {
        DefaultKaptcha defaultKaptcha = getKaptcha();
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(KAPTCHA_SESSION_KEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            return jpegOutputStream.toByteArray();
        } catch (IllegalArgumentException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断验证码是否正确
     *
     * @param request
     * @param code
     * @return
     */
    public static boolean verifyCode(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        if (StringUtils.equalsIgnoreCase(sessionCode, code)) {
            return true;
        } else {
            return false;
        }
    }

}
