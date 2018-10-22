package org.stathry.smartj.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.stathry.smartj.model.ColumnFieldMap;
import org.stathry.smartj.model.TableBeanMap;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据库信息映射为bean相关工具
 * Created by dongdaiming on 2018-10-18 11:47
 */
public class DBMappingUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBMappingUtils.class);
    private static final Map<String, String> MYSQL_TYPE2JAVA = ConfigManager.getObj("dataTypeMap", "mysql.dataTypeToJavaMap", Map.class);
    private static final Map<String, String> MYSQL_TYPE2MYBATIS = ConfigManager.getObj("dataTypeMap", "mysql.dataTypeToMyBatisJdbcType", Map.class);
    private static final List<String> EX_COLS = ConfigManager.getObj("template", "orm.insert.exclude.columns", List.class);

    public static TableBeanMap getTableBeanMap(Connection conn, JdbcTemplate template, String schema, String tableName) throws SQLException {
        if(StringUtils.isBlank(tableName)) {
            return null;
        }
        List<TableBeanMap> list = getTableBeanMapList(conn, template, schema, Arrays.asList(tableName), null);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static List<TableBeanMap> getTableBeanMapList(Connection conn, JdbcTemplate template, String schema) throws SQLException {
        return getTableBeanMapList(conn, template, schema, null, null);
    }

    public static List<TableBeanMap> getTableBeanMapList(Connection conn, JdbcTemplate template, String schema, List<String> tableNames) throws SQLException {
        return getTableBeanMapList(conn, template, schema, tableNames, EX_COLS);
    }

    public static List<TableBeanMap> getTableBeanMapList(Connection conn, JdbcTemplate template, String schema,
                                                         List<String> tableNames, List<String> exInsertFields) throws SQLException {
        DatabaseMetaData db = conn.getMetaData();
        LOGGER.info("database version {} {}.", db.getDatabaseProductName(), db.getDatabaseProductVersion());

        exInsertFields = exInsertFields == null ? new ArrayList<>() : exInsertFields;

        List<Map<String, Object>> tables = DBUtils.queryTables(template, schema, tableNames);
        if(tables == null || tables.isEmpty()) {
            return null;
        }

        List<TableBeanMap> beans = new ArrayList<>(tables.size());
        TableBeanMap bean;
        List<Map<String, Object>> columns;
        List<ColumnFieldMap> fields;
        List<ColumnFieldMap> iFields;
        List<ColumnFieldMap> keyFields;
        ColumnFieldMap field;
        String curTable;
        for(Map<String, Object> t : tables) {
            bean = initTableBeanMap(t);

            curTable = bean.getTable();
            if((columns = DBUtils.queryColumns(template, schema, curTable)) == null || columns.isEmpty()) {
                continue;
            }

            fields = new ArrayList<>(columns.size());
            iFields = new ArrayList<>(columns.size());
            keyFields = new ArrayList<>(2);

            for(Map<String, Object> c : columns) {
                field = initColumnFieldMap(c);
                addColumnFieldMap(field, fields, iFields, exInsertFields, keyFields);
            }

            bean.setFields(fields);
            bean.setInsertFields(iFields);
            bean.setKeyFields(keyFields);
            beans.add(bean);
        }
        return beans;
    }

    private static void addColumnFieldMap(ColumnFieldMap field, List<ColumnFieldMap> fields, List<ColumnFieldMap> iFields,
                                          List<String> exInsertFields, List<ColumnFieldMap> keyFields) {
        if (field.isPriKey()) {
            keyFields.add(field);
            fields.add(0, field);
        } else {
            fields.add(field);
        }
        if (!exInsertFields.contains(field.getColumn())) {
            iFields.add(field);
        }
    }

    private static ColumnFieldMap initColumnFieldMap(Map<String, Object> c) {
        ColumnFieldMap field = new ColumnFieldMap();
        String column = String.valueOf(c.get("column_name"));
        field.setColumn(column.toLowerCase());
        field.setName(underlineNameToCamel(column));

        String cType = formatColumnType(c, field).toLowerCase();
        field.setJdbcType(MYSQL_TYPE2MYBATIS.get(cType).toUpperCase());
        field.setType(MYSQL_TYPE2JAVA.get(cType));

        field.setComment(underlineNameToCamel(String.valueOf(c.get("column_comment"))));

        field.setPriKey("PRI".equalsIgnoreCase((String)c.get("COLUMN_KEY")));

        return field;
    }

    private static String formatColumnType(Map<String, Object> c, ColumnFieldMap field) {
        String type = String.valueOf(c.get("column_type"));
        int index = type.indexOf('(');
        type = index != -1 ? type.substring(0, type.indexOf('(')) : type;
        return type;
    }

    private static TableBeanMap initTableBeanMap(Map<String,Object> t) {
        TableBeanMap bean = new TableBeanMap();
        String curTable = (String)t.get("table_name");
        bean.setTable(curTable);
        bean.setSimpleClassName(StringUtils.capitalize(underlineNameToCamel(curTable)));
//        bean.setIdType("long");
//        bean.setIdJdbcType("BIGINT");
//        bean.setIdName("id");
//        bean.setIdColumn("id");
        bean.setDesc(formatTableComment((String) t.get("table_comment")));
        return bean;
    }

    private static String formatTableComment(String comment) {
        if (comment != null && comment.length() > 0 && comment.endsWith("表")) {
            int i = comment.lastIndexOf("表");
            comment = comment.substring(0, i);

        }
        return comment;
    }

    public static String underlineNameToCamel(String name) {
        if(StringUtils.isBlank(name)) {
            return "";
        }
        if(!name.contains("_")) {
            return StringUtils.isAllUpperCase(name) ? name.toLowerCase() : StringUtils.uncapitalize(name);
        }

        String[] a = name.split("_");
        StringBuilder temp = new StringBuilder();
        temp.append(StringUtils.uncapitalize(a[0]));
        for(int i = 1; i < a.length; i++) {
            temp.append(StringUtils.capitalize(a[i]));
        }
        return temp.toString();
    }
}
