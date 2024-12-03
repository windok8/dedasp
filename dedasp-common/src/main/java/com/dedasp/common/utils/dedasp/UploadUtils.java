package com.dedasp.common.utils.dedasp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UploadUtils {

    public static List<String> initTime(){
        CopyOnWriteArrayList<String> timeList = new CopyOnWriteArrayList<>();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

        timeList.add(currentDate.format(yearFormatter));
        timeList.add(currentDate.format(monthFormatter));
        timeList.add(currentDate.format(dayFormatter));

        return timeList;
    }
}
