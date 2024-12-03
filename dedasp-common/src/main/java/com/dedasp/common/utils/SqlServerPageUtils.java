package com.dedasp.common.utils;

public class SqlServerPageUtils {
    public static Integer initPageNo(Integer pageNo){
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        return pageNo;
    }
    public static Integer initPageSize(Integer pageSize){
        if (pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        return pageSize;
    }
}
