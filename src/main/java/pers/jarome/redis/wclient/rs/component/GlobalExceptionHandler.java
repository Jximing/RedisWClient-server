package pers.jarome.redis.wclient.rs.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import pers.jarome.redis.wclient.rs.util.WebUtils;
import pers.jarome.redis.wclient.common.web.vo.EncryptEntity;
import pers.jarome.redis.wclient.common.web.vo.ResultEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jarome
 * @date 2018/1/2
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 自定义异常处理
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        ModelAndView mav = null;
        //判断是否ajax请求
        if (WebUtils.isAjax(request)) {
            mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject(EncryptEntity.encodeEncrypt(ResultEntity.error(exception.getMessage())));
        } else {
            mav = new ModelAndView("/error");
            String message = exception.getMessage();
            if (StringUtils.isBlank(message)) {
                message = "未知错误";
            }
            mav.addObject("message", message);
        }
        return mav;
    }


}
