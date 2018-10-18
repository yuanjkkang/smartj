package org.stathry.smartj.swing;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.stathry.smartj.commons.enums.DBDataTypeEnums;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO
 * Created by dongdaiming on 2018-10-18 14:11
 */
public class StringTest {

    @Test
    public void testCastColumnToJavaType() throws Exception {
//        System.out.println(castColumnTypeToJava("double"));
//        System.out.println(castColumnTypeToJava("varchar(200)"));
//        System.out.println(castColumnTypeToJava("smallint(5) unsigned"));
        List<String> list = FileUtils.readLines(new File("/temp/column-types.txt"));
        Set<String> set = new TreeSet<>();
        for (String t : list) {
            set.add(castColumnTypeToJava(t));
        }
        System.out.println(set.size());
        System.out.println(JSON.toJSONString(set));
    }

    private String castColumnTypeToJava(String type) {
        int index = type.indexOf('(');
        type = index != -1 ? type.substring(0, type.indexOf('(')) : type;
        return type;
    }
}
