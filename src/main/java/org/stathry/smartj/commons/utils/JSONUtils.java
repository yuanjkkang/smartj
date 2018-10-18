package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * JSONUtils
 *
 * @author stathry
 * @date 2018/4/16
 */
public class JSONUtils {

    private JSONUtils() {}

    public static void main(String[] args) {
        System.out.println(completeJSON("\"set\",\"mediumtext\""));
        System.out.println(completeJSON("k1,k2"));
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

}
