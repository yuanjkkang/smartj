package org.stathry.smartj.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取应用上下午
 * @author dongdaiming
 * @date 2017年8月14日
 */
public class ApplicationContextUtils implements ApplicationContextAware {
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtils.context = applicationContext;
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

}
