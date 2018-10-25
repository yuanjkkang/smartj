/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.smartj.commons.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongdaiming@free.com
 *
 *         2016年8月22日
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	private static final String LINE_SEP = SystemUtils.LINE_SEPARATOR;
	private static final String CHARSET = "UTF-8";

	public static boolean exists(String path) {
		if (StringUtils.isBlank(path)) {
			return false;
		}
		return new File(path).exists();
	}

	public static boolean createFile(String path, boolean forceNew) throws IOException {
		if (StringUtils.isBlank(path)) {
			return false;
		}
		return createFile(new File(path), forceNew);
	}

	public static boolean createFile(File file, boolean forceNew) throws IOException {
		if (file == null) {
			return false;
		}
		boolean fex = file.exists();
		boolean r;
		if(forceNew && fex) {
		    file.delete();
            r = file.createNewFile();
        } else if(forceNew && !fex) {
            file.getParentFile().mkdirs();
            r = file.createNewFile();
        } else if(!forceNew && fex) {
		    r = true;
        } else {
            file.getParentFile().mkdirs();
            r = file.createNewFile();
        }
		return r;
	}

	public static File getResourceFile(String name) throws IOException {
        File tempFile = null;
        ClassPathResource tr = new ClassPathResource("classpath*:" + name);
        try {
            tempFile = tr.getFile();
        } catch (IOException e) {
            //ignore
        }
        return tempFile;
    }

    public static String getJarPath(String tag) {
        URL url = FileUtils.class.getResource(tag);
        String urlPath=url.toString();//jar:file:/D:/eclipse/workspace/test_url/target/test_url-0.0.1-SNAPSHOT.jar!/META-INF
        // jar:file:/D:/workspace3/smartj/target
        int index=urlPath.indexOf(".jar!/"+tag);
        return  urlPath.substring(0, index+5);
    }

    public static void main(String[] args) throws MalformedURLException {
//        System.out.println(JSON.toJSONString(System.getenv()));
//        System.out.println(JSON.toJSONString(System.getProperties()));
//        System.out.println(getJarPath("temp"));
        System.out.println(ResourceUtils.extractJarFileURL(new URL("jar:file:/C:/mypath/myjar.jar")));
    }

}
