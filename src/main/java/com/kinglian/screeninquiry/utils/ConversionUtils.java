package com.kinglian.screeninquiry.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConversionUtils {
    private final static String nameFrefix = "set";
    private static Logger logger = LoggerFactory.getLogger(ConversionUtils.class);
    //将model中与entity中类型和名称相同的属性值赋值给对应的entity的属性，并返回entity
    public static <T1, T2> T2 TypeConversion(T1 model, T2 entity,List<String> list) {
        List<Map<String, Object>> modelList = getFiledInfo(model);
        List<Map<String, Object>> entityList = getFiledInfo(entity);
        for (Map e : entityList) {
            for (Map m : modelList) {

                if (list.contains(m.get("name").toString()))
                {
                    continue;
                }
                /**
                 * 判断类型和属性名是否都相同
                 */
                if (e.get("type").toString().equals(m.get("type").toString()) && e.get("name")
                        .toString()
                        .equals(m.get("name").toString())) {
                    try {
                        Field f = entity.getClass().getDeclaredField(e.get("name").toString());
                        f.setAccessible(true);
                        f.set(entity, m.get("value"));
                    } catch (Exception ex) {//查看其父类属性
                        try {
                            Field f = entity.getClass().getSuperclass().getDeclaredField(e.get("name").toString());
                            f.setAccessible(true);
                            f.set(entity, m.get("value"));
                        } catch (Exception e1) {
                            logger.error(ex.getMessage(), "conversion类型转换错误 " + ex);
                        }
                    }
                }
            }
        }
        return entity;
    }

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private static List<Map<String, Object>> getFiledInfo(Object o) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(o.getClass().getDeclaredFields()));

        /**
         * 如果存在父类，获取父类的属性值，类型，名称并添加到一起
         */
        Class sc = o.getClass().getSuperclass();
        if (sc != null) {
            fields.addAll(Arrays.asList(sc.getDeclaredFields()));
        }
        for (Field field : fields) {
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("type", field.getType().toString());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), o));
            list.add(infoMap);
        }
        return list;
    }
    public static Object mapConvertBean(Map<String, Object> map, Object obj) {
        /*
         * Class类是反射的入口 一般获得一个Class对象有三种途径 1.类属性方式，如String.class
         * 2.对象的getClass方法加载，如new String().getClass().
         * 3.forName方法加载，如Class.forName("java.lang.String") 用于动态加载 比如加载驱动
         * 这里我传入一个Object对象,所以我用的是第2种
         */
        Class classz = obj.getClass();
        // 得到传入实体类所有的方法(getXxx setXxx ...)
        // Method[] declaredMethods = classz.getDeclaredMethods();

        // 判断map集合参数不能为null
        if (!map.isEmpty()) {
            for (Map.Entry<String, Object> keyValue : map.entrySet()) {
                // 得到map键值
                String propertyName = keyValue.getKey();
                // 得到map-value值
                Object value = keyValue.getValue();
                // 得到回属性名
                Field field = getClassField(classz, propertyName);

                if (field != null) {
                    // 获取属性类型
                    Class<?> fieldType = field.getType();
                    value  = convertValType(value, fieldType);
                    Method method = null;
                    try {
                        // 得到属性set方法名
                        String setMethodName = convertKey(propertyName);
                        //得到方法
                        method = classz.getMethod(setMethodName, field.getType());
                        //判断是否能够执行（这个可以不要）
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(obj, value);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
        return obj;
    }

    /**
     * 注意：转化map集合的key 例如 属性名 xXxx(tNode)类型 Eclipse自动生成get set方法第一个字母是不会大写的
     *
     * @return
     */
    public static String convertKey(String propertyName) {
        // 将属性名第一个字母大写然后进行拼接
        String setMethodName = nameFrefix.concat(propertyName.substring(0, 1).toUpperCase().concat(propertyName.substring(1)));
        return setMethodName;
    }

    /**
     * 得到属性名
     *
     * @param clazz
     *            类
     * @param fieldName
     *            属性名
     * @return
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        // 传入类是Object类型或者是null直接return
        if (clazz == null || Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value  Object对象值
     * @param fieldType 属性的类型
     * @return 转换后的值
     */
    private static Object convertValType(Object value, Class<?> fieldType) {
        Object retVal = null;
        if (Long.class.getName().equals(fieldType.getName()) || long.class.getName().equals(fieldType.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldType.getName())
                || int.class.getName().equals(fieldType.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldType.getName())
                || float.class.getName().equals(fieldType.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldType.getName())
                || double.class.getName().equals(fieldType.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else if (Boolean.class.getName().equals(fieldType.getName())
                || boolean.class.getName().equals(fieldType.getName())) {
            retVal = Boolean.parseBoolean(value.toString());
        } else if (Character.class.getName().equals(fieldType.getName())
                || char.class.getName().equals(fieldType.getName())) {
            retVal = value;
        } else if(Date.class.getName().equals(fieldType.getName())){
            retVal = strConvertDate(value.toString());
        } else if(String.class.getName().equals(fieldType.getName())){
            retVal = value;
        }
        return retVal;
    }


    /**
     * String类型转Date
     * @param
     * @return
     */
    public static Date strConvertDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }



}
