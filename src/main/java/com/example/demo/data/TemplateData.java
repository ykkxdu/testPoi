package com.example.demo.data;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 模板数据生成
 *
 * @param <T> 模板实体类
 */
public class TemplateData<T> {
    /**
     * 填充数据
     *
     * @param map    填充目标
     * @param obj    填充对象
     * @return 填充后的数据
     */
    public HashMap<String,Object> fill(HashMap<String,Object> map, T obj)
    {
        if(obj==null)
        {
            return map;
        }
        // 通过反射拿到填充对象的所有属性getDeclaredFields。
        try {
            Field[] fields=obj.getClass().getDeclaredFields();
            for(Field field:fields)
            {
                field.setAccessible(true);// 设置可见性为公开
                String fieldName=field.getName();// 获取属性名称
                String fieldTypeName=field.getType().getName();// 获取属性类型
                if(fieldTypeName.equals("java.lang.String"))
                {
                    map.put(fieldName,isNullThenEmpty((String) field.get(obj)));
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  map;
    }
    /**
     * 如果值为 null 则返回空字符串
     *
     * @param str 字符串
     * @return
     */
    public static String isNullThenEmpty(String str)
    {
        return str==null ? "":str;
    }

}
