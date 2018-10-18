package org.stathry.smartj.commons.enums;

/**
 * MySQLDataTypeJavaMap
 * Created by dongdaiming on 2018-10-18 14:27
 */
public enum MySQLDataTypeJavaMap {
    BIGINT(""),

    ;

//    ["bigint","bit","blob","char","date","datetime","decimal","double","enum","float","geometry","int","longblob","longtext","mediumblob","mediumint","mediumtext","set","smallint","text","time","timestamp","tinyint","varchar","year"]

    private String type;

    private MySQLDataTypeJavaMap(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static String getTypeByName(String name) {
        if(name == null || name.length() == 0) {
            return name;
        }
        for(MySQLDataTypeJavaMap e : values()) {
            if(e.name().equalsIgnoreCase(name.trim())) {
                return e.type();
            }
        }
        return name;
    }

    public static String getNameByType(String type) {
        if(type == null || type.length() == 0) {
            return type;
        }
        String t = type.trim().toLowerCase();
        String et;
        for(MySQLDataTypeJavaMap e : values()) {
            et = e.type().toLowerCase();
            if(et.equals(t) || et.startsWith(t)) {
                return e.name();
            }
        }
        return type;
    }
}
