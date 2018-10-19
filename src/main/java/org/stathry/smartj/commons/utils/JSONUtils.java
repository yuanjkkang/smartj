package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JSONUtils
 *
 * @author stathry
 * @date 2018/4/16
 */
public class JSONUtils {

    private static final String LINE_SEP = SystemUtils.LINE_SEPARATOR;
    private static final String TAB = "    ";
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private JSONUtils() {}

    public static void main(String[] args) {
//        System.out.println(completeJSON("\"set\",\"mediumtext\""));
//        System.out.println(completeJSON("k1,k2"));
//        String s = "{\"k1\":\"v1\",\"k2\":\"v2\", \"k3\":\"v3\", \"k4\":4}";
//        String s = "{\"k1\":\"v1\",\"k2\":\"v2\", \"k3\":3, \"data\": {\"k11\":\"v11\",\"k22\":\"v22\", \"k33\":33}}";
//        String s = "{\"k1\":\"v1\",\"k2\":\"v2\", \"k3\":3, \"data\": {\"k11\":\"v11\",\"k22\":\"v22\", \"k33\":33, \"detail\": {\"k111\":\"v111\",\"k222\":\"v222\", \"k333\":333}}}";
        String s = "{\"k1\":\"v1\",\"k2\":\"v2\", \"k3\":3, \"k4\":[\"e41\", \"e42\"], \"data\": {\"k11\":\"v11\",\"k22\":\"v22\", \"k33\":33, \"detail\": {\"k111\":\"v111\",\"k222\":\"v222\", \"k333\":333}}}";
        System.out.println(s);
        System.out.println();
        System.out.println(beautyJSON(s));
    }

    public static String completeJSON(String s) {
        if(StringUtils.isBlank(s)) {
            return "";
        }
        String[] a = s.split(",");
        JSONObject json = new JSONObject(a.length * 2);
        for (String e : a) {
            e = e.contains("\"") ? e.replaceAll("\"", "") : e;
            json.put(e, "");
        }
        return json.toJSONString();
    }

    public static String beautyJSON(String s) {
        if(StringUtils.isBlank(s)) {
            return "";
        }
        AtomicInteger ai = new AtomicInteger();
        StringBuilder b = new StringBuilder();
        if(s.startsWith("[")) {
            JSONArray a = JSON.parseArray(s);
            beautyJSONArray(a, b, ai);
        } else if(s.startsWith("{")){
            JSONObject o = JSON.parseObject(s);
            beautyJSONObject(o, b, ai);
        } else {
            throw new IllegalArgumentException("not a json:" + s);
        }
        return b.toString();
    }

    private static void beautyJSONObject(JSONObject o, StringBuilder b, AtomicInteger ai) {
        int c = ai.getAndIncrement();
        int i = 0;
        int size = o.size();
        b.append(LINE_SEP).append(StringUtils.repeat(TAB, c)).append("{");
        c++;
        Object n;
        for (Map.Entry<String, Object> e : o.entrySet()) {
            b.append(LINE_SEP).append(StringUtils.repeat(TAB, c));
            n = e.getValue();
            b.append("\"").append(e.getKey()).append("\"").append(" : ");
            beautyByValueType(n, b, ai);
            if(i < size - 1) {
                b.append(",");
            }
            i++;
        }
        b.append(LINE_SEP).append(StringUtils.repeat(TAB, c)).append("}");
    }

    private static void beautyJSONArray(JSONArray a, StringBuilder b, AtomicInteger ai) {
        int c = ai.getAndIncrement();
        b.append(LINE_SEP).append(StringUtils.repeat(TAB, c)).append("[");
        Object e;
        for (int i = 0, size = a.size(); i < size; i++) {
            b.append(LINE_SEP).append(StringUtils.repeat(TAB, c));
            e = a.get(i);
            beautyByValueType(e, b, ai);
            if(i < size - 1) {
                b.append(",");
            }
        }
        b.append(LINE_SEP).append(StringUtils.repeat(TAB, c)).append("]");
    }

    private static void beautyByValueType( Object n, StringBuilder b, AtomicInteger ai) {
        if(n instanceof JSONArray) {
            beautyJSONArray((JSONArray) n, b, ai);
        } else if(n instanceof JSONObject) {
            beautyJSONObject((JSONObject) n, b, ai);
        } else if(n instanceof String) {
            b.append("\"").append((String)n).append("\"");
        } else {
            b.append(n);
        }
    }

}
