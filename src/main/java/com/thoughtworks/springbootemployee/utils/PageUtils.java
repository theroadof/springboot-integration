package com.thoughtworks.springbootemployee.utils;

import java.util.List;

public class PageUtils<T> {

    public List<T> getPage(List<T> list, int currentPage, int pageSize) {
        int firstIndex = (currentPage - 1) * pageSize;
        int lastIndex = currentPage * pageSize;
        return list.subList(firstIndex, lastIndex);
    }
}
