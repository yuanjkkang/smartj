package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author stathry
 * @date 2018/4/16
 */
public class SmartJsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartJsonUtils.class);
    private static final String  EMPTY_JSON = "{}";


    private SmartJsonUtils() {}

    public static String format(String src) {
        if(StringUtils.isBlank(src)) {
            return EMPTY_JSON;
        }
        String r = EMPTY_JSON;
        try {
            JSON.parse(src);
        } catch (Exception e) {
            LOGGER.error("invalid json:{}", src);
            LOGGER.error("parse json error.", e);
        }
        return r;
    }
}
