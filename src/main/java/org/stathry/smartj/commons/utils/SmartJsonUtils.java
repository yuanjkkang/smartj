package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TODO
 *
 * @author stathry
 * @date 2018/4/16
 */
public class SmartJsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartJsonUtils.class);
    private static final String  EMPTY_JSON = "{}";
    private static final String Q = "\"";
    private static final String C = ",";
    private static final String  SEP = ":";
    private static final String  L = SystemUtils.LINE_SEPARATOR;


    private SmartJsonUtils() {}

    public static String format(String src) {
        if(StringUtils.isBlank(src)) {
            return EMPTY_JSON;
        }
        String r = EMPTY_JSON;
        Object json = null;
        try {
            json = JSON.parse(src);
        } catch (Exception e) {
            LOGGER.error("invalid json:{}", src);
            LOGGER.error("parse json error.", e);
        }
        if(json == null) {
        return r;
        }

        StringBuilder b = new StringBuilder();
        append(b, json);
        return b.toString();
    }

    private static void append(StringBuilder b, Object json) {
        if(json.getClass() == JSONObject.class) {
            JSONObject o = (JSONObject)json;
            b.append('{');
            b.append(L);
            for(Map.Entry<String, Object> e : o.entrySet()) {
                appendKV(b, e);
            }
            b.deleteCharAt(b.lastIndexOf(C));
            b.append('}');
        } else {
            b.append('[');
            JSONArray a = (JSONArray) json;
            b.append(']');
        }
    }

    private static void appendKV(StringBuilder b, Map.Entry<String, Object> e) {
        b.append(Q);
        b.append(e.getKey());
        b.append(Q);
        b.append(SEP);
        b.append(Q);
        if(e.getValue().getClass() == JSONObject.class) {

        }
        b.append(e.getValue());
        b.append(Q);
        b.append(C);
        b.append(L);
    }

    public static void main(String[] args) {
        System.out.println(format("{\"k1\":\"v1\", \"k2\":\"v2\"}"));
    }
}
