package com._520.myblog.utils;

import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Werdio丶
 * @since 2020-06-01 13:03:29
 */
public class StringUtil {

    public static List<Long> str2Long(String ids){

        if (StringUtils.isEmpty(ids)){
            return Collections.emptyList();
        }

        List<Long> longList = new ArrayList<>();

        for (String str : ids.split(",")) {
            longList.add(Long.parseLong(str));
        }

        return longList;
    }
}
