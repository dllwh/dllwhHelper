package org.dllwh.utils.apache.poi.excel.util;

import org.dllwh.utils.apache.poi.excel.annotation.Excel;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

import org.dllwh.utils.apache.poi.excel.annotation.*;

import java.lang.reflect.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: Java注解帮助类
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-10-19 23:24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class AnnotationHelper {
    /**
     * 获取Class中设置的ExcelField注解信息
     *
     * @param cls cls Class
     */
    public static List<Object[]> getAnnotationList(Class<?> cls) {
        List<Object[]> annotationList = new ArrayList<>();

        // 获得有注解的字段
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            addAnnotationListByField(annotationList, f, "", "");
            Excel excel = f.getAnnotation(Excel.class);
            if (excel != null) {
                Class<?> fieldClazz = f.getType();
                // 判断是否为集合类型
                if (Collection.class.isAssignableFrom(fieldClazz)) {
                    Type fc = f.getGenericType();
                    // 如果是泛型参数的类型
                    if (fc instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) fc;
                        //得到泛型里的class类型对象。
                        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                        Field[] fs2 = genericClazz.getDeclaredFields();
                        for (Field f2 : fs2) {
                            addAnnotationListByField(annotationList, f2, "list", f.getName());
                        }
                        Method[] ms2 = genericClazz.getDeclaredMethods();
                        for (Method m2 : ms2) {
                            addAnnotationListByMethod(annotationList, m2, "list", f.getName());
                        }
                    }
                } else {
                    Field[] fs2 = fieldClazz.getDeclaredFields();
                    for (Field f2 : fs2) {
                        addAnnotationListByField(annotationList, f2, "bean", f.getName());
                    }
                    Method[] ms2 = fieldClazz.getDeclaredMethods();
                    for (Method m2 : ms2) {
                        addAnnotationListByMethod(annotationList, m2, "bean", f.getName());
                    }
                }
            }
        }
        // 获得有注解的方法
        Method[] methodArr = cls.getDeclaredMethods();
        for (Method method : methodArr) {
            addAnnotationListByMethod(annotationList, method, "", "");
            Excel excel = method.getAnnotation(Excel.class);
            if (excel != null) {
                Class<?> fieldClazz = method.getReturnType();
                // 判断是否为集合类型
                if (Collection.class.isAssignableFrom(fieldClazz)) {
                    Type fc = method.getGenericReturnType();
                    // 如果是泛型参数的类型
                    if (fc instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) fc;
                        // 得到泛型里的class类型对象。
                        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                        Field[] fs2 = genericClazz.getDeclaredFields();
                        for (Field f2 : fs2) {
                            // 首字母转小写
                            addAnnotationListByField(annotationList, f2, "list", uncapitalize(method.getName().substring(3)));
                        }
                        Method[] methodArr2 = genericClazz.getDeclaredMethods();
                        for (Method method2 : methodArr2) {
                            // 首字母转小写
                            addAnnotationListByMethod(annotationList, method2, "list", uncapitalize(method.getName().substring(3)));
                        }
                    }
                } else {
                    Field[] fields = fieldClazz.getDeclaredFields();
                    for (Field field : fields) {
                        // 首字母转小写
                        addAnnotationListByField(annotationList, field, "bean", uncapitalize(method.getName().substring(3)));
                    }
                    Method[] methods = fieldClazz.getDeclaredMethods();
                    for (Method method3 : methods) {
                        // 首字母转小写
                        addAnnotationListByMethod(annotationList, method3, "bean", uncapitalize(method.getName().substring(3)));
                    }
                }
            }
        }
        // 对字段排序
        annotationList.sort(Comparator.comparingInt(o -> ((ExcelField) o[0]).orderNum()));
        return annotationList;
    }

    /**
     * 查找方法中设有ExcelField的信息，并将信息记入annotationList中
     *
     * @param method       方法对象
     * @param mark         标记是否是bean对象还是list对象，还是基础对象
     * @param propertyName f所属类在Bean对象（即setSheet传入的Class<?> cls参数）中对应的字段名
     */
    private static void addAnnotationListByMethod(List<Object[]> annotationList, Method method, String mark, String propertyName) {
        ExcelField excelField = method.getAnnotation(ExcelField.class);
        if (excelField != null) {
            // 首字母转小写
            annotationList.add(new Object[]{excelField, uncapitalize(method.getName().substring(3)), mark, propertyName});
        }
    }

    /**
     * 查找字段中设有ExcelField的信息，并将信息记入annotationList中
     *
     * @param field        字段对象
     * @param mark         标记是否是bean对象还是list对象，还是基础对象
     * @param propertyName 所属类在Bean对象（即setSheet传入的Class<?> cls参数）中对应的字段名
     */
    private static void addAnnotationListByField(List<Object[]> annotationList, Field field, String mark, String propertyName) {
        ExcelField excelField = field.getAnnotation(ExcelField.class);
        if (excelField != null) {
            annotationList.add(new Object[]{excelField, field.getName(), mark, propertyName});
        }
    }
}
