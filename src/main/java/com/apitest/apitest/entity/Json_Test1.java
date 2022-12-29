package com.apitest.apitest.entity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Json_Test1 {
    public static void main(String[] args) {
        //建立JSONObject
        JSONObject json = new JSONObject();
        //新增屬性
        json.put("username", "張三");
        json.put("password", "123");
        //列印
        System.out.println(json);

        //增加屬性
        json.element("sex", "男");
        json.put("age", 18);
        System.out.println(json);

        //根據key返回
        System.out.println(json.get("sex"));

        //判斷輸出物件的型別
        boolean isArray = json.isArray();
        boolean isEmpty = json.isEmpty();
        boolean isNullObject = json.isNullObject();
        System.out.println("是否陣列：" + isArray + ", 是否空：" + isEmpty + ", 是否空為空物件：" + isNullObject);

        System.out.println("=====");

        //把JSONArray新增到JSONObject中
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "張三");
        jsonArray.add(1, "123");
        //開始新增
        json.element("student", jsonArray);
        System.out.println(json);
    }
}
