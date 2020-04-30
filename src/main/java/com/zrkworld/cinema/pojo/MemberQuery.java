package com.zrkworld.cinema.pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
/**
 * @author zrk
 */
public class MemberQuery {
    /**
     * 姓名查询条件
     */
    private String name;
    /**
     * 生日查询条件
     */
    private String birthdayQuery;
    /**
     * 开始索引数
     */
    private Integer startIndex;
    /**
     * 结束索引数
     */
    private Integer endIndex;


    /**
     * format date
     **/

    public static String formatDate(Date date, String format) {
        try {
            if (StringUtils.isBlank(format)) {
                format = "yyyy-MM-dd";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception var3) {
            return "";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdayQuery() {
        return birthdayQuery;
    }

    public void setBirthdayQuery(String order) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-Integer.parseInt(order));

        this.birthdayQuery = formatDate(calendar.getTime(),"");
    }


    public void setPageIndex(Integer pageIndex,Integer pageSize) {
        this.startIndex = (pageIndex-1)*pageSize;
        this.endIndex = startIndex+pageSize;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }
}
