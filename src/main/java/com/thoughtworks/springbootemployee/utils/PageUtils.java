package com.thoughtworks.springbootemployee.utils;

import java.util.List;

public class PageUtils {

    public static List<? extends Object> getPage(List<? extends Object> list, int currentPage, int pageSize) {
        int firstIndex = (currentPage - 1) * pageSize;
        int lastIndex = currentPage * pageSize;
        return list.subList(firstIndex, lastIndex);
    }
}
