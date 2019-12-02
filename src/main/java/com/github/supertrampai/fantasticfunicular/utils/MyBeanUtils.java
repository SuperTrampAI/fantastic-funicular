/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;


public class MyBeanUtils extends PropertyUtilsBean {

    /**
     * 对象拷贝
     * 数据对象空值不拷贝到目标对象
     * @param databean
     * @param tobean
     * @throws Exception
     */
    public static void copyBeanNotNull2Bean(Object databean,Object tobean)
    {
        PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(databean);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) {
                continue;
            }
            if (PropertyUtils.isReadable(databean, name) &&PropertyUtils.isWriteable(tobean, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean, name);
                    if(value!=null){
                        PropertyUtils.setSimpleProperty(tobean, name, value);
                    }
                }
                catch(Exception e){

                }

            }
        }
    }
}
