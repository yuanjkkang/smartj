package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * SmartJsonUtils
 *
 * @author stathry
 * @date 2018/4/16
 */
public class SmartJsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartJsonUtils.class);
    private static final String  L = SystemUtils.LINE_SEPARATOR;
    private static final Pattern PS = Pattern.compile(",");


    private SmartJsonUtils() {}

    public static String completeJSON(String s) {
        if(StringUtils.isBlank(s)) {
            return "";
        }
        String[] a = s.split(",");
        JSONObject json = new JSONObject(a.length * 2);
        for (String e : a) {
            json.put(e, "");
        }
        return json.toJSONString();
    }

    public static void main(String[] args) {
//        System.out.println(format("['e1', 'e2']"));
//        System.out.println(format("[1, 2]"));
    }

}
