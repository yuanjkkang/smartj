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
 * MyBatisJavaModelGenerator
 */
@Component
public class MyBatisJavaModelGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisJavaModelGenerator.class);
    
    @Autowired
    private ORMTemplateContext templateContext;
    @Value("${jdbc.schema}")
    private String schema;
    @Value("${orm.template.timePattern}")
    private String timePattern;
    
    public void generateByTemplate(TableBeanMap beanInfo) throws Exception {
        generateByTemplate(beanInfo, schema);
    }
    public void generateByTemplate(TableBeanMap beanInfo, String schema) throws Exception {
        ORMTemplateContext tc = templateContext;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new ClassPathResource(tc.getTemplateDir()).getFile());
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        String templateName = tc.getMybatisTemplateName();
        Template template = cfg.getTemplate(templateName);

        tc.setGenerateTime(DateFormatUtils.format(new Date(), timePattern));

        Writer out = null;
        try {
        	tc.setTable(beanInfo.getTable());
            tc.setFields(beanInfo.getFields());
            tc.setClzz(beanInfo.getSimpleClassName());
            tc.setDesc(beanInfo.getDesc());
            File file = new File(tc.getTargetPath()  + schema + "/model/" + beanInfo.getSimpleClassName() + ".java");
            FileUtils.createFile(file, true);
            LOGGER.info("java file has been generated, path is \"{}\".", file.getAbsolutePath());
            out = new FileWriter(file);
            template.process(tc, out);
            out.flush();  
        } catch(Exception e) {
            LOGGER.error("generateJavaBean error," + e.getMessage(), e);
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }
    
}
