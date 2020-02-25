package com._520.myblog.utils;

import com._520.myblog.entity.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据实体类生成 sql 语句
 */
public class CreateTable {
    private final static Map<String, String> map = new HashMap<>();

    static {
        map.put("Integer", "int(10)");
        map.put("Long", "bigint(20)");
        map.put("boolean", "int(1)");
        map.put("LocalDate", "datetime");
        map.put("String", "VARCHAR(255)");
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(createTable(Blog.class));
//        System.out.println(createTable(Comment.class));
//        System.out.println(createTable(Tag.class));
//        System.out.println(createTable(Type.class));
//        System.out.println(createTable(User.class));

//        System.out.println(createFile("myblog.sql"));

        StringBuilder sql = new StringBuilder(createTable(Blog.class));
        sql.append(createTable(Comment.class));
        sql.append(createTable(Tag.class));
        sql.append(createTable(Type.class));
        sql.append(createTable(User.class));
        System.out.println(sql.toString());
        writeSqlToFile(sql.toString());
    }

    /**
     *  将 sql 写入文件
     * @throws IOException
     */
    private static void writeSqlToFile(String sql) throws IOException {
        FileWriter writer = new FileWriter(new File(createFile("myblog.sql")));
        writer.write(sql);
        writer.close();
    }

    /**
     *  创建指定名称的文件
     * @throws IOException
     */
    private static String createFile(String name) throws IOException {
        File file = new File("");
        StringBuilder url = new StringBuilder(file.getCanonicalPath());
        url.insert(2, "\\");
        StringBuilder fileName = new StringBuilder(url);
        fileName.append("\\").append("\\").append(name);
        File sqlFile = new File(fileName.toString());
        System.out.println(sqlFile.createNewFile());

        return fileName.toString();
    }

    /**
     *  创建指定实体类的 sql 语句
     * @return  创建表的 sql
     */
    private static String createTable(Class<?> clz) {
        StringBuilder tableName = new StringBuilder();
//        System.out.println(clz.getName());
        String substring = clz.getName().substring(clz.getName().lastIndexOf(".") + 1);
        String s = substring.substring(0, 1).toLowerCase();
        // 表名
        tableName.append(s).append(substring.substring(1));
//        System.out.println(tableName.toString());

        Field[] declaredFields = clz.getDeclaredFields();
//        System.out.println(declaredFields.length);

        StringBuilder result = new StringBuilder();
        result.append("create table ").append(tableName).append(" ( \r\n");
//        System.out.println(result.toString());
        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//            System.out.println(declaredField.getType().getName());
            String fieldName = declaredField.getName();
            String typeName1 = declaredField.getType().getName();
            String typeName = typeName1.substring(typeName1.lastIndexOf(".") + 1);
//            System.out.println(typeName);
            if ("id".equals(fieldName)){
                result.append("    ").append(fieldName).append(" ").append(map.get(typeName)).append(" NOT NULL AUTO_INCREMENT").append(",\r\n");
            }else {
                result.append("    ").append(fieldName).append(" ").append(map.get(typeName)).append(",\r\n");
            }
        }

//        result.deleteCharAt(result.lastIndexOf(","));
        result.append("    PRIMARY KEY (id)").append("\r\n");
        result.append(");\r\n");
//        System.out.println(result.toString());

        return result.toString();
    }
}