package org.stathry.smartj.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模板上下文
 * 
 * @author dongdaiming
 */
@Component
public class ORMTemplateContext {

    @Value("${orm.template.dir}")
    private String templateDir;

    @Value("${orm.template.jpaTemplateName}")
    private String jpaTemplateName;

    @Value("${orm.template.mybatisTemplateName}")
    private String mybatisTemplateName;

    @Value("${orm.template.targetPath}")
    private String targetPath;

    @Value("${orm.template.copyright}")
    private String copyright;

    @Value("${orm.template.author}")
    private String author;

    @Value("${orm.template.pkg}")
    private String pkg;
    
    private String clzz;
    
    private String table;
    
    private String desc;

    private String generateTime;

    private List<ColumnFieldMap> fields;
    private List<ColumnFieldMap> insertFields;
    private List<ColumnFieldMap> keyFields;

    @Override
    public String toString() {
        return "ORMTemplateContext{" +
                "templateDir='" + templateDir + '\'' +
                ", jpaTemplateName='" + jpaTemplateName + '\'' +
                ", mybatisTemplateName='" + mybatisTemplateName + '\'' +
                ", targetPath='" + targetPath + '\'' +
                ", copyright='" + copyright + '\'' +
                ", author='" + author + '\'' +
                ", pkg='" + pkg + '\'' +
                ", clzz='" + clzz + '\'' +
                ", table='" + table + '\'' +
                ", desc='" + desc + '\'' +
                ", generateTime='" + generateTime + '\'' +
                ", fields=" + fields +
                ", insertFields=" + insertFields +
                ", keyFields=" + keyFields +
                '}';
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getJpaTemplateName() {
        return jpaTemplateName;
    }

    public void setJpaTemplateName(String jpaTemplateName) {
        this.jpaTemplateName = jpaTemplateName;
    }

    public String getMybatisTemplateName() {
        return mybatisTemplateName;
    }

    public void setMybatisTemplateName(String mybatisTemplateName) {
        this.mybatisTemplateName = mybatisTemplateName;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public String getClzz() {
        return clzz;
    }

    public void setClzz(String clzz) {
        this.clzz = clzz;
    }

    public List<ColumnFieldMap> getFields() {
        return fields;
    }

    public void setFields(List<ColumnFieldMap> fields) {
        this.fields = fields;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

    public List<ColumnFieldMap> getInsertFields() {
        return insertFields;
    }

    public void setInsertFields(List<ColumnFieldMap> insertFields) {
        this.insertFields = insertFields;
    }

    public List<ColumnFieldMap> getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(List<ColumnFieldMap> keyFields) {
        this.keyFields = keyFields;
    }
}
