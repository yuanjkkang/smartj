package org.stathry.smartj.commons.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.stathry.smartj.commons.enums.DBDataTypeEnums;
import org.stathry.smartj.model.ColumnFieldMap;
import org.stathry.smartj.model.TableBeanMap;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据库操作工具类
 * Created by dongdaiming on 2018-09-29 14:58
 */
public class DBUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);

    private static final Map<Integer, DruidDataSource> cachedDataSource = new HashMap<>();

    private static final boolean COLLUMN_CONVERT = true;

    private static final Set<String> EX_COLS = ConfigManager.getObj("template", "orm.insert.exclude.columns", Set.class);

    public static String concatInSQL(List<String> list) {
        StringBuilder builder = new StringBuilder("(");
        for (int i = 0, size = list.size(); i < size; i++) {
            if(i != 0) {
                builder.append(", '");
            } else {
                builder.append("'");
            }
            builder.append(list.get(i));
            builder.append("'");
        }
        return builder.append(')').toString();
    }

    public static List<String> queryColumns(JdbcTemplate jdbcTemplate, String schema, String table) {
        return jdbcTemplate.queryForList("select column_name from INFORMATION_SCHEMA.Columns where table_name=? and table_schema=? ", String.class, table, schema);
    }

    public static synchronized DruidDataSource getDataSource(Map<String, String> params) {
        DruidDataSource dataSource;
        Integer hash = params.hashCode();
        if ((dataSource = cachedDataSource.get(hash)) != null) {
            return dataSource;
        }
        dataSource = new DruidDataSource();
        dataSource.setUrl(params.get("url"));
        dataSource.setUsername(params.get("username"));
        dataSource.setPassword(params.get("password"));
        dataSource.setDriverClassName(params.get("driverClassName"));

        String maxActive = params.get("maxActive");
        String initialSize = params.get("initialSize");
        String maxWait = params.get("maxWait");

        dataSource.setMaxActive(StringUtils.isBlank(maxActive) ? 2 : Integer.parseInt(maxActive));
        dataSource.setInitialSize(StringUtils.isBlank(initialSize) ? 1 : Integer.parseInt(initialSize));
        dataSource.setMaxWait(StringUtils.isBlank(maxWait) ? 3000L : Long.parseLong(maxWait));
        dataSource.setValidationQuery("SELECT 1");
        cachedDataSource.put(hash, dataSource);
        return dataSource;
    }

    public static Connection tryConn(Map<String, String> jdbcParams) {
        DruidDataSource dataSource;
        Connection conn = null;
        try {
            dataSource = getDataSource(jdbcParams);
            conn = dataSource.getConnection();
            if(conn == null && dataSource != null) {
                dataSource.close();
            }
        } catch (SQLException e) {
            LOGGER.error("get db connection error.", e);
        }

        LOGGER.info("get connection of database[{}] result {}.", jdbcParams.get("url"), conn != null);
        return conn;
    }

    public static void releaseConn(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }



}
