package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {

    private static final String NAME_BASE = "conf" ;
    public static final String NAME_CONF = "config";
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> PROPS = new ConcurrentHashMap<>(4);

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(ConfigManager.get("jdbc", "jdbc.schema")));
        System.out.println(JSON.toJSONString(ConfigManager.getObj("template", "orm.insert.exclude.columns", Set.class)));
    }
    private ConfigManager() {}

    public static String get(String key) {
        if(StringUtils.isBlank(key)) return "";
        return StringUtils.trimToEmpty(getMap(NAME_CONF).get(key));
    }

    public static <T> T getObj(String key, Class<T> clazz) {
        if(StringUtils.isBlank(key)) return null;
        return JSON.parseObject(getMap(NAME_CONF).get(key), clazz);
    }

    public static String get(String resourceName, String key) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(resourceName)) return "";
        return StringUtils.trimToEmpty(getMap(resourceName).get(key));
    }

    public static String get(String resourceName, String key, String defaultValue) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(resourceName)) return "";
        String v = getMap(resourceName).get(key);
        return v == null ? defaultValue : v;
    }

    public static <T> T getObj(String resourceName, String key, Class<T> clazz) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(resourceName)) return null;
        return JSON.parseObject(getMap(resourceName).get(key), clazz);
    }

    public static ConcurrentHashMap<String, String> getMap(String name) {
        ConcurrentHashMap p = PROPS.get(name);
        if(p != null) {
            return p;
        }

        synchronized (ConfigManager.class) {
            p = PROPS.get(name);
            if(p != null) {
                return p;
            }
            Properties pp = loadProperties(name);
            if(pp == null) {
                return new ConcurrentHashMap();
            }
            ConcurrentHashMap pn = new ConcurrentHashMap(pp);
            PROPS.put(name, pn);
            return pn;
        }
    }

    private static Properties loadProperties(String name) {
        Properties prop = new Properties();
        String resourceName = NAME_BASE + "/" + name + ".properties";
        PathMatchingResourcePatternResolver pr = new PathMatchingResourcePatternResolver();
        try(InputStream in = pr.getResource(resourceName).getInputStream();
            Reader r = new InputStreamReader(in, "UTF-8")) {
            prop.load(r);
        } catch (Exception e) {
        }
        Assert.notEmpty(prop, "loadProperties " + name + " error.");
        return prop;
    }

}
