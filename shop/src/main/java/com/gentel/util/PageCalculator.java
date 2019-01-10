package com.gentel.util;

/*
 * @Author Gentel
 * @Date 2019-01-10 23:33
 */

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
