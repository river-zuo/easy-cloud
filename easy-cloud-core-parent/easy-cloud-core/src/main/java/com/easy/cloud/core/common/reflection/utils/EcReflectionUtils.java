package com.easy.cloud.core.common.reflection.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.easy.cloud.core.basic.utils.EcBaseUtils;

/**
 * 反射工具类
 * @author daiqi
 * @date 2018年1月8日 下午7:46:12
 */
public class EcReflectionUtils {
	
	/**
	 * 
	 * <p>获取指定class类及其继承的所有父类除了Object类的属性</p>
	 *
	 * <pre></pre>
	 *
	 * @param targetClass
	 * @return
	 *
	 * @author daiqi
	 * @创建时间  2018年5月4日 下午10:14:33
	 */
	public static List<Field> getDeclaredFieldsIncSup(final Class<?> targetClass) {
		List<Field> fieldList = new ArrayList<>() ;
		Class<?> tempClass = targetClass;
		//当父类为null的时候说明到达了最上层的父类(Object类).
		while (EcBaseUtils.isNotNull(tempClass)) {
		      fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
		      tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
		}
		return fieldList;
	}
	
	/**
	 * 
	 * <p>
	 * 创建class对应的实例对象
	 * </p>
	 * @param clazz : Class : 泛型class
	 * @return 泛型对象
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午10:18:55
	 */
	public static <T> T newInstance(Class<T> clazz){
		if(EcBaseUtils.isNull(clazz)){
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	* <p>
	* 拼接某属性的 get方法
	* </p >
	* @author tudou
	* @date 2018/8/14 11:27
	* @param fieldName 需要拼接的属性名称
	* @return java.lang.String
	*/
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "get"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}

	/**
	* <p>
	* 拼接在某属性的 set方法
	* </p >
	* @author tudou
	* @date 2018/8/14 11:27
	* @param fieldName 需要拼接的属性名称
	* @return java.lang.String
	*/
	public static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "set"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}
}
