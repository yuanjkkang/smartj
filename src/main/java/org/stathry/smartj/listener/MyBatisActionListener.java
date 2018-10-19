package org.stathry.smartj.listener;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.stathry.smartj.commons.utils.ApplicationContextUtils;
import org.stathry.smartj.commons.utils.ConfigManager;
import org.stathry.smartj.commons.utils.DBMappingUtils;
import org.stathry.smartj.commons.utils.DBUtils;
import org.stathry.smartj.constant.ActionCommand;
import org.stathry.smartj.generator.MyBatisJavaModelGenerator;
import org.stathry.smartj.generator.MyBatisMapperGenerator;
import org.stathry.smartj.model.TableBeanMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSONActionListener
 * Created by dongdaiming on 2018-10-16 11:00
 */
public class MyBatisActionListener implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisActionListener.class);

    private static final String TARGET_PATH = ConfigManager.get("template", "orm.template.targetPath");

    private static final MyBatisJavaModelGenerator myBatisJavaModelGenerator = ApplicationContextUtils.getBean(MyBatisJavaModelGenerator.class);
    private static final MyBatisMapperGenerator myBatisMapperGenerator = ApplicationContextUtils.getBean(MyBatisMapperGenerator.class);

    private JTextField classField;
    private JTextField urlField;
    private JTextField nameField;
    private JTextField pwdField;
    private JTextField tableField;
    private JTextField pkgField;
    private JTextArea showField;

    public MyBatisActionListener() {
        super();
    }

    public MyBatisActionListener(JTextField classField, JTextField urlField, JTextField nameField, JTextField pwdField, JTextField tableField, JTextField pkgField, JTextArea showField) {
        this.classField = classField;
        this.urlField = urlField;
        this.nameField = nameField;
        this.pwdField = pwdField;
        this.tableField = tableField;
        this.pkgField = pkgField;
        this.showField = showField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        Map<String, String> jdbcParams = loadJdbcParams();

        Connection conn = null;
        if (ActionCommand.MYBATIS_TEST.equals(cmd)) {
            conn = DBUtils.tryConn(jdbcParams);
            showField.setText(conn != null ? "连接成功" : "连接失败");
        } else if(ActionCommand.MYBATIS_GENERATE.equals(cmd)) {
            conn = DBUtils.tryConn(jdbcParams);
            DruidDataSource dataSource = DBUtils.getDataSource(jdbcParams);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String tables = tableField.getText();
            List<String> tableNames = StringUtils.isBlank(tables) ? null : Arrays.asList(tables.split(","));
            String url = jdbcParams.get("url");
            String schema = url.substring(url.lastIndexOf("/") + 1, url.length());

            startGenerateMybatisFile(conn, jdbcTemplate, tableNames, schema);
        } else {
            LOGGER.warn("not supported cmd {}.", cmd);
        }
        DBUtils.releaseConn(conn);
    }

    private void startGenerateMybatisFile(Connection conn, JdbcTemplate jdbcTemplate, List<String> tableNames, String schema) {
        try {
            List<TableBeanMap> models = DBMappingUtils.getTableBeanMapList(conn, jdbcTemplate, schema, tableNames);
            for (TableBeanMap m : models) {
                myBatisJavaModelGenerator.generateByTemplate(m, schema, pkgField.getText());
                myBatisMapperGenerator.generateByTemplate(m, schema);
            }
            showField.setText("生成成功!文件路径：" + new File(TARGET_PATH + schema).getAbsolutePath());
        } catch (Exception e1) {
            LOGGER.error("generate mybatis file error.", e1);
        }
    }


    private Map<String, String> loadJdbcParams() {
        Map<String, String> map = new HashMap<>();
        map.put("driverClassName", classField.getText());
        map.put("url", urlField.getText());
        map.put("username", nameField.getText());
        map.put("password", pwdField.getText());
        return map;
    }
}
