/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ConvertUtils {
	/**
	 * 将map装换为javabean对象
	 * 
	 * @param map
	 * @param T
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map map, Class T) throws Exception {
		if (map == null || map.size() == 0) {
			return null;
		}
		// 对象字段名称
		String fieldname = "";
		// 对象方法需要赋的值
		Object methodsetvalue = "";
		Field fields[] = T.getDeclaredFields();
		T bean = (T) T.newInstance();
		// 遍历所有字段，对应配置好的字段并赋值
		for (Field field : fields) {
			if (null != field) {
				// 获取字段名称
				fieldname = field.getName();
				// 获取data里的对应值
				methodsetvalue = map.get(fieldname);
				if (null != methodsetvalue) {
					if (field.getType() == Date.class) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// Change this condition so that it does not always evaluate to "false"
						// methodsetvalue = methodsetvalue == null ? null : sdf.parse(methodsetvalue.toString());
						methodsetvalue = sdf.parse(methodsetvalue.toString());
					}
					BeanUtils.copyProperty(bean, fieldname, methodsetvalue);
				}
			}
		}
		return bean;
	}

	/**
	 * 根据List<Map<String, Object>>数据转换为JavaBean数据
	 * 
	 * @param listMap
	 * @param T
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> ListMap2JavaBean(List<Map<String, Object>> listMap, Class T) throws Exception {
		List<T> beanList = new ArrayList<T>();
		for (int i = 0, n = listMap.size(); i < n; i++) {
			Map<String, Object> map = listMap.get(i);
			T bean = mapToBean(map, T);
			beanList.add(bean);
		}
		return beanList;
	}
}
