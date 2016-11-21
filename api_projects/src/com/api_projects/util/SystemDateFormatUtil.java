package com.api_projects.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SystemDateFormatUtil {
    
    /**
     * 系统格式化时�?年月�?
     */
    public static final String FORMAT_DATE="yyyy-MM-dd";
    
    public static final String FORMAT_DATE_TMP="yyyyMMdd";
    
    public static final String FORMAT_DATE_YM="yyyyMM";
    
    public static final String FORMAT_MONTH="yyyy-MM";
    
    /**
     * 系统格式化时间年月日 时分�?
     */
    public static final String FORMAT_DATETIME="yyyy-MM-dd HH:mm:ss";
    
    /**
     * 系统格式化时间年月日 时分
     */
    public static final String FORMAT_DATETIME_NOTSECOND="yyyy-MM-dd HH:mm";
    
    /**
     * 系统格式化时�?时分�?
     */
    public static final String FORMAT_TIME="hh:mm:ss";
    
    /**
     * 系统格式化时�?时分 24小时�?
     */
    public static final String FORMAT_TIME_NOTSECOND="HH:mm";
    
    public static final String FORMAT_DATETIME_SECOND="yyyyMMddHHmmsss";
    

    public static String formatDate(Date d,String format){
        if(null==d){
            return null;
        }
        return new SimpleDateFormat(format).format(d);
    }
    
    public static String formatTime(Time d,String format){
        if(null==d){
            return null;
        }
        return new SimpleDateFormat(format).format(d);
    }
    
    public static String formatTimestamp(Timestamp d,String format){
        if(null==d){
            return null;
        }
        return new SimpleDateFormat(format).format(d);
    }
    
    public static Date StringToDate(String dateStr,String fmt){
    	if(dateStr!=null&&!dateStr.trim().equals("")){
    		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    		try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    public static Time StringToTime(String timeStr,String fmt){
    	Time reTime = null;
    	if(timeStr!=null && !timeStr.trim().equals("")){
    		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    		try {
    			reTime =  new Time(sdf.parse(timeStr).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				reTime = null;
			}
    	}
    	return reTime;
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"
     * @param timeStr
     * @return
     */
    public static Timestamp StringToTimestamp(String timeStr){
    	Timestamp reTimestamp = null;
    	
    	if(timeStr!=null && !timeStr.trim().equals("")){
    		try {
    			reTimestamp =  Timestamp.valueOf(timeStr);
			} catch (Exception e) {
				e.printStackTrace();
				reTimestamp = null;
			}
    	}
    	return reTimestamp;
    }
    
    public static Timestamp getDefTimestamp(){
    	return Timestamp.valueOf(SystemDateFormatUtil.formatDate(new Date(), SystemDateFormatUtil.FORMAT_DATETIME));
    }
    
    /** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getSpecifiedDayBefore(Date date) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1); 
  
        String dayBefore = new SimpleDateFormat(FORMAT_DATE_TMP).format(c.getTime());  
        return dayBefore;  
    }  

    /** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getSpecifiedDayBefore(Date date,String fomatStr) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1); 
  
        String dayBefore = new SimpleDateFormat(fomatStr).format(c.getTime());  
        return dayBefore;  
    } 
    
    /** 
     * 获得指定日期的 numDay(正数为后n天，负数为前n天)
     *  
     * @param specifiedDay 
     * @return 
     */  
    public static String getSpecifiedDayAfter(String specifiedDay,int numDay) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat(FORMAT_DATE).parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + numDay);  
  
        String dayAfter = new SimpleDateFormat(FORMAT_DATE)  
                .format(c.getTime());  
        return dayAfter;  
    }  
    
    /**
     * 计算两个时间的秒数差
     * @param start
     * @param type
     * @throws ParseException
     */
    public static long getBetweenTime(Date start, Date end){
        long between = (end.getTime() - start.getTime()) / 1000;// 除以1000是为了转换成秒
        return between;
     }
}
