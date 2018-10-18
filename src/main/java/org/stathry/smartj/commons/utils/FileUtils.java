/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.smartj.commons.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

}
