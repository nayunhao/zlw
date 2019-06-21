package com.zlead.utils;

import java.util.concurrent.TimeUnit;

import com.zlead.thread.Constants;
/**
 * 时间单位工具
 * 
 * @author yangting
 *         
 */
public class TimeUtil {
    
    public static TimeUnit getTimeUnit(int unit) {
        TimeUnit rtn = null;
        switch (unit) {
            case Constants.TIME_UNIT_DAYS:
                rtn = TimeUnit.DAYS;
                break;
            case Constants.TIME_UNIT_HOURS:
                rtn = TimeUnit.HOURS;
                break;
            case Constants.TIME_UNIT_MICROSECONDS:
                rtn = TimeUnit.MICROSECONDS;
                break;
            case Constants.TIME_UNIT_MILLISECONDS:
                rtn = TimeUnit.MILLISECONDS;
                break;
            case Constants.TIME_UNIT_MINUTES:
                rtn = TimeUnit.MINUTES;
                break;
            case Constants.TIME_UNIT_NANOSECONDS:
                rtn = TimeUnit.NANOSECONDS;
                break;
            case Constants.TIME_UNIT_SECONDS:
            default:
                rtn = TimeUnit.SECONDS;
                break;
        }
        return rtn;
    }
    
    public static boolean timeout(long interval, int timeout, TimeUnit unit) {
        if (timeout <= 0)
            return false;
        boolean result = false;
        TimeUnit ut = unit;
        if (ut == null)
            ut = TimeUnit.MILLISECONDS;
        switch (ut) {
            case SECONDS:
                result = interval >= TimeUnit.SECONDS.toMillis(timeout) ? true : false;
                break;
            case MINUTES:
                result = interval >= TimeUnit.MINUTES.toMillis(timeout) ? true : false;
                break;
            case HOURS:
                result = interval >= TimeUnit.HOURS.toMillis(timeout) ? true : false;
                break;
            case DAYS:
                result = interval >= TimeUnit.DAYS.toMillis(timeout) ? true : false;
                break;
            case MILLISECONDS:
                result = interval >= timeout ? true : false;
                break;
            case MICROSECONDS:
                result = interval >= TimeUnit.MICROSECONDS.toMillis(timeout) ? true : false;
                break;
            case NANOSECONDS:
                result = interval >= TimeUnit.NANOSECONDS.toMillis(timeout) ? true : false;
                break;
            default:
                break;
        }
        return result;
    }
}
