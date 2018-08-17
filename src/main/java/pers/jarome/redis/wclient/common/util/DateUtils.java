package pers.jarome.redis.wclient.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jarome
 * @date 2017/12/22
 **/
public class DateUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    private static DateFormat getDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 格式化时间
     *
     * @param source
     * @param pattern
     * @return date
     */
    public static Date formatByString(String source, String pattern) {
        try {
            return getDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            LOGGER.error("DateUtils formatByString error", e);
            return null;
        }
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return string
     */
    public static String formatByDate(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }
}
