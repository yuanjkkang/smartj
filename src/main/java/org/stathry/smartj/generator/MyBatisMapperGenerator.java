package org.stathry.smartj.generator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.stathry.smartj.commons.utils.FileUtils;
import org.stathry.smartj.model.ORMTemplateContext;
import org.stathry.smartj.model.TableBeanMap;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;

/**
 * mapper文件生成
 * Created by dongdaiming on 2018-07-10 09:54
 */
@Component
public class MyBatisMapperGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisMapperGenerator.class);

    @Autowired
    private ORMTemplateContext templateContext;

    @Value("${jdbc.schema}")
    private String schema;
    @Value("${orm.template.mapperTemplateName}")
    private String mapperTempName;
    @Value("${orm.template.timePattern}")
    private String timePattern;

    public void generateByTemplate(TableBeanMap tableBeanMap) throws Exception {
        generateByTemplate(tableBeanMap, schema);
    }

    public void generateByTemplate(TableBeanMap tableBeanMap, String schema) throws Exception {
        ORMTemplateContext tc = templateContext;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new ClassPathResource(tc.getTemplateDir()).getFile());
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        Template template = cfg.getTemplate(mapperTempName);

            tc.setTable(tableBeanMap.getTable());
            tc.setFields(tableBeanMap.getFields());
            tc.setClzz(tableBeanMap.getSimpleClassName());
            tc.setDesc(tableBeanMap.getDesc());
            tc.setInsertFields(tableBeanMap.getInsertFields());
            tc.setKeyFields(tableBeanMap.getKeyFields());
            tc.setGenerateTime(DateFormatUtils.format(new Date(), timePattern));
        Writer out = null;
        try {
            File file = new File(tc.getTargetPath() + schema + "/mapper/" + tableBeanMap.getSimpleClassName() + "Mapper.xml");
            FileUtils.createFile(file, true);
            LOGGER.info("mapper file has been generated, path is \"{}\".", file.getAbsolutePath());
            out = new FileWriter(file);
            template.process(tc, out);
            out.flush();
        } catch(Exception e) {
            LOGGER.error("generate mapper error," + e.getMessage(), e);
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }


}
