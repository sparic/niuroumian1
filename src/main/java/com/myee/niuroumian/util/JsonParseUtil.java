package com.myee.niuroumian.util;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description json��ʽ����ת��������
 */
public class JsonParseUtil {

    /**
     *
     * @description ������ת����json��ʽ���ַ���

     */
    public static String objectToJson(Object obj) {
        JSONObject jsonStr = JSONObject.fromObject(obj);
        return jsonStr.toString();
    }

    /**
     *
     * @description ��json��ʽ���ַ���ת���ɾ������
     */
    public static Object jsonToObject(String json, Class<?> c) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        return JSONObject.toBean(jsonObj, c);
    }

    /**
     *
     * @description ������ת����json��ʽ����
     */
    public static String listToJson(List<?> list) {
        JSONArray array = JSONArray.fromObject(list);
        String jsonStr = array.toString();
        return jsonStr;
    }

    /**
     *
     * @description ��json��ʽת��������
     */
    public static List<?> jsonToList(String json, Class<?> c) {
        JSONArray array = JSONArray.fromObject(json);
        List<?> list = (List<?>) JSONArray.toCollection(array, c);
        return list;
    }
}
