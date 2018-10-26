package org.stathry.smartj.commons.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * FreeMakerConfigUtils
 * Created by dongdaiming on 2018-10-26 10:17
 */
public class FreeMakerConfigUtils {

    private static volatile Configuration cfg;

    private static Configuration initCfg() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        String dir = ConfigManager.get("template", "orm.template.dir");
        cfg.setClassForTemplateLoading(FreeMakerConfigUtils.class, dir);
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        return cfg;
    }

    public static Configuration getCfg() {
        if(cfg == null) {
            synchronized (FreeMakerConfigUtils.class) {
                if (cfg == null) {
                    cfg = initCfg();
                }
            }
        }
        return cfg;
    }
}
