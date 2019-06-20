package com.zsc.base.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 日期转换类 转换一个 java.util包.Date 对象到一个字符串以及 一个字符串到一个 java.util包.Date 对象.
 * @author icelove
 */
public final class DateUtils {
	public final static SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyyMMdd");
	public final static String dateAndTime = "yyyy-MM-dd HH:mm:ss";
	public final static String datePattern = "yyyy-MM-dd";
	public final static String timePattern = "HH:mm";
	public final static String GMTPattern = "EEE,d MMM yyyy HH:mm:ss z";
	public final static String TIMESTAMP_PATTERN = "yyyyMMddHHmmss";
	public static final String dayNames[] = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六" };
	/**
	 * 提取时间的部分参数
	 * @param time
	 * @param field
	 * @return
	 */
	public final static int getField(Date time,int field){
		if(time==null)return -1;
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		return cal.get(field);
	}
	/**
	 * 创建毫秒是0的时间对象
	 * @return
	 */
	public static Date timeNow(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	/**
	 * 返回年月日格式化字符串"yyyy-MM-dd"
	 * @return
	 */
	public static String getDatePattern() {
		return datePattern;
	}
	/**
	 * 返回当天时间字符串:2008-10-15
	 * @return 时间字符串
	 */
	public static String today() {
		Calendar cal = Calendar.getInstance();
		DecimalFormat f = new DecimalFormat("00");
		StringBuffer date = new StringBuffer();
		date.append(cal.get(Calendar.YEAR)).append("-").append(f.format(cal.get(Calendar.MONTH) + 1)).append("-").append(f.format(cal.get(Calendar.DAY_OF_MONTH)));
		return date.toString();
	}
    /**
     * 获取当前时间的指定格式
     * @param format
     * @return
     */
	public final static String getCurrDate(String format) {
		return format(format,new Date());
	}
	/**
	 * 返回yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public static final String formatTime(Date date) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(dateAndTime);
			returnValue = df.format(date);
		}
		return (returnValue);
	}
	public static final String timestamp(){
		return new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date());
	}
	public static final String timestamp(Date time){
		return new SimpleDateFormat(TIMESTAMP_PATTERN).format(time);
	}
	public static final String dateAndTime(){
		return new SimpleDateFormat(dateAndTime).format(new Date());
	}
	/**
	 * 根据日期格式，返回日期按datePattern格式转换后的字符串.
	 * @param date the a date
	 * @return the string
	 */
	public static final String formatDate(Date date) {
		return format(datePattern,date);
	}
	/**
	 *  返回字符串时间格式: HH:mm
	 * @param theTime
	 * @return
	 */
	public static String formatTimePattern(Date theTime) {
		return format(timePattern, theTime);
	}
	/**
	 * 根据一个时间格式字符串串，返回一个字符串
	 * @param format 时间字符串
	 * @param date 要格式化的时间数据
	 */
	public static final String format(String format, Date date) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(format);
			returnValue = df.format(date);
		}
		return (returnValue);
	}
	/**
	 * 格式化时间到"yyyy-MM-dd"
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseToDate(String strDate) throws ParseException {
		return parse(datePattern, strDate);
	}
	/**
	 * 格式换时间戳"yyyy-MM-dd HH:mm:ss"
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseToTime(String strDate) throws ParseException {
		return parse(dateAndTime, strDate);
	}
	public static final Date parseToTimeNoExc(String strDate){
		try{
			return parse(dateAndTime, strDate);
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 自定义格式化字符串
	 * @param format   格式化
	 * @param strDate 时间字符串
	 * @return
	 * @throws ParseException
	 */
	public static final Date parse(String format, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(format);
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}
	public static final Date parse(String format, String strDate,Date defDate){
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(format);
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return defDate;
		}
		return (date);
	}
    /**
     * 获得当前日期
     * @return int
     */
    public static int dateOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
    /**
     * 返回日期的天
     * #DAY_OF_MONTH
     * @param date Date
     * @return int
     */
    public static int dateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    /**
     * 获得当前月份
     * @return int
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获得当前月份
     * @return int
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    /**
     * 获取一个时间的星期几 从 1-7
     * @param date
     * @return
     */
    public static int getMyWeek(Date date){
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(date);
    	int week = ca.get(Calendar.DAY_OF_WEEK)-1;
    	return week==0?7:week;
    }
    /**
     * 短日期字符串获取星期,格式化异常返回当前值
     * @param dateStr format=20170206
     * @return
     */
    public static String getMyWeekName(String dateStr){
		try{
			if(dateStr!=null && dateStr.length()==8){
				return getMyWeekName(shortDateFormat.parse(dateStr));
			}
		}catch(ParseException e){
			return dateStr;
		}
    	return dateStr;
    }
    /**
     * 返回 周一 到  周日
     * @param date
     * @return
     */
    public static String getMyWeekName(Date date){
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(date);
    	int week = ca.get(Calendar.DAY_OF_WEEK)-1;
    	return new String[]{"周日","周一","周二","周三","周四","周五","周六"}[week];
    }
    /**
     * 获得当前年份
     * @return int
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    
    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long dayDiff(Date date1,Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }
    /**
     * 计算两个日期相差的小时数，如果date2 > date1 返回正数，否则返回负数
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long hourDiff(Date date1,Date date2) {
        return (date2.getTime() - date1.getTime()) / 3600000;
    }
    
    /**
     * 获取每月的第一周
     * @param year
     * @param month
     * @return 返回所在星期的第几天
     * @author lujun
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.set(year, month - 1,1);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取每月的最后一周 返回时 0-6 值
     * @param year
     * @param month
     * @return
     * @author lujun
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.set(year, month - 1,getDaysOfMonth(year,month));
        return c.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取本月第一天
	 * @param year
     * @param month
     * @return
     */
    public static Date getFirstTimeOfMonth(int year, int month) {
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
    }

    /**
     * 获取本月最后一天
	 * @param year
	 * @param month
     * @return
     */
    public static Date getLastTimeOfMonth(int year, int month) {
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		//修正毫秒级偏差 
		//SQL SERVER中DATETIME表示的时间为00:00:00到23:59:59.997，它的时间精度为1/300秒，在使用时会舍入到舍入到 .000、.003 或 .007 秒三个增量
		cal.set(Calendar.MILLISECOND, 997);
		return cal.getTime();
    }
    /**
     * 获取某年某月的天数
     * @param year int
     * @param month int 月份[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /**
     * 获得某月的天数
     * @param year int
     * @param month int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8") || month.equals("10") || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            }else{
                days = 28;
            }
        }
        return days;
    }

	/**
	 * 按照日期格式，将字符串解析为日期对象.
	 * @param gmtStr 输入字符串的格式,如yyyy-MM-dd hh:mm:ss
	 * @return Date 对象
	 */
	public static final Date GMT2Date(String gmtStr) throws ParseException {
		return parse(GMTPattern, gmtStr);
	}
	
	/**
	 * 返回当前日期到时间24：00：00 用于查询该日期的内容
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Date dateStartDate(Date date){
		return resetTime(date,0,0,0,0);
	}

	/**
	 * 返回当前日期到时间 00:00:00.000 用于查询该日期的内容
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static final Date dateStartDate(String dateStr) throws ParseException {
		String dastr = dateStr + " 00:00:00";
		Date date = parse(dateAndTime, dastr);
		return resetTime(date,0,0,0,0);
	}

	/**
	 * 返回当前日期到时间24：00：00 用于查询该日期的内容
	 * 因为通用的约束是<end 所以这个日期要取后一天的00:00:00.000
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Date dateEndDate(Date date){
		date = add(date,Calendar.DAY_OF_MONTH,1);
		return resetTime(date,0,0,0,0);
	}

	/**
	 * 返回当前日期到时间 23:59:59.999 用于查询该日期的内容
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static final Date dateEndDate(String dateStr) throws ParseException {
		if(dateStr.length()==10){
			//yyyy-MM-dd
			Date date = parse(datePattern,dateStr);
			date = add(date,Calendar.DAY_OF_MONTH,1);
			return resetTime(date,0,0,0,0);
		}else if(dateStr.length()==19){
			Date date = parse(dateAndTime, dateStr);
			date = add(date,Calendar.DAY_OF_MONTH,1);
			return resetTime(date,0,0,0,0);
		}
		throw new ParseException("格式错误",0);
	}
	/**
	 * 返回当天的Calendar对象
	 * @return the current date
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(parseToTime(todayAsString));
		return cal;
	}
	/**
	 * 两个日期相差的天数
	 * 如果返回正数 date1 在  date0 几天
	*/ 
	public static int daysBetween(Date date1,Date date0){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis(); 
		cal.setTime(date0);
		long time2 = cal.getTimeInMillis(); 
		long between_days=(time2-time1)/(1000*3600*24); 
		return Integer.parseInt(String.valueOf(between_days));
	}
	/**
	 * 返回时间格式的带有num位随机数的字符串
	 * @param num
	 * @return
	 */
	public static final String getDateNO(int num) {
		return getDateNO(new Date(),num);
	}
	/**
	 * 返回时间字符串(没有间隔符)"yyyyMMddHHmmss"
	 * @param date
	 * @param num
	 * @return
	 */
	public static final String getDateNO(Date date, int num) {
		Random jjj = new Random();
		if (num == 0){
			return format("yyyyMMddHHmmss", date);
		}
		String jj = "";
		for (int k = 0; k < num; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return format("yyyyMMddHHmmss", date) + jj;
	}
	/**
	 * 返回前后多少年，月，日
	 * @param field Calendar.year...
	 * @return
	 */
	public static Date getNextDay(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 根据当前一个时期，取得一个时间的部分,用下面方法比getDateTime("MM",new Date());方法速度快两倍
	 * @param date
	 * @param w
	 * @return
	 */
	public static String partDate(Date date, int w) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DecimalFormat f = new DecimalFormat("00");
		switch (w) {
			case 1: {
				return String.valueOf(cal.get(Calendar.YEAR));
			}case 2: {
				return f.format(cal.get(Calendar.MONTH) + 1);
			}case 3: {
				return f.format(cal.get(Calendar.DAY_OF_MONTH));
			}case 4: {
				return f.format(cal.get(Calendar.HOUR_OF_DAY));
			}case 5: {
				return f.format(cal.get(Calendar.MINUTE));
			}case 6: {
				return f.format(cal.get(Calendar.SECOND));
			}default:return "00";
		}
	}

	/**
	 * 返回当前日期 时间为0
	 * @return
	 */
	public static Date getOnlyDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 判断日期是否是今天
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date){
		return formatDate(new Date()).equals(formatDate(date));
	}
	
    /**
     * 判断日期是否有效,包括闰年的情况
     * @param date YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }
    /**
     * 判断时间是否有效
     * @param shortTime HH:mm
     * @return
     */
    public static boolean isShortTime(String shortTime) {
    	if(StringUtils.isEmpty(shortTime)){
    		return false;
		}
    	if(shortTime.length()!=5){
    		return false;
		}
    	String[] items = StringUtils.split(shortTime,":");
    	if(items.length!=2 || items[0].length()!=2 || items[1].length()!=2){
    		return false;
		}
    	int hour = NumberUtils.str2Int(items[0],-1);
    	int min = NumberUtils.str2Int(items[1],-1);
    	if(hour==-1 || min==-1){
    		return false;
		}
    	if(hour<0 || hour>23){
    		return false;
		}
    	if(min<0 || min>59){
    		return false;
		}
    	return true;
    }
    /**
     * 判断时间是否有效
     * @param time HH:mm:ss
     * @return
     */
    public static boolean isTime(String time){
	  Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	  return p.matcher(time).matches();
	}
	/**
	 * 获取微博表现时间实现
	 * @param date
	 * @return
	 */
    public static String weiboTime(Date date){
    	String str = "";
    	if(date==null){
    		return str;
		}
    	Date now = new Date();
    	Date yestoday = new Date(now.getTime()-24*60*60*1000);
    	SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
    	String nowStr = sdf.format(now);
    	String yesStr = sdf.format(yestoday);
    	String dateStr = sdf.format(date);
    	if(nowStr.equals(dateStr)){
    		SimpleDateFormat msdf = new  SimpleDateFormat("HH:mm");
    		str ="今天 " + msdf.format(date);
    	}else if(yesStr.equals(dateStr)){
    		SimpleDateFormat msdf = new  SimpleDateFormat("HH:mm");
    		str ="昨天 " + msdf.format(date);
    	}
    	else{
    		SimpleDateFormat msdf = new  SimpleDateFormat("MM-dd HH:mm");
    		str = msdf.format(date);
    	}
		return str;
    }
	/**
	 * 重置一个时间
	 * @param time
	 * @param hour 如果小于-1则忽略
	 * @param minute 如果小于-1则忽略
	 * @param second 如果小于-1则忽略
	 * @param millisecond 如果小于-1则忽略
	 * @return
	 */
	public static Date resetTime(Date time,int hour,int minute,int second,int millisecond){
		return resetTime(time.getTime(),hour,minute,second,millisecond);
	}
	public static Date resetTime(long millis,int hour,int minute,int second,int millisecond){
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(millis);
		if(hour>-1){
			ca.set(Calendar.HOUR_OF_DAY,hour);
		}
		if(minute>-1){
			ca.set(Calendar.MINUTE,minute);
		}
		if(second>-1){
			ca.set(Calendar.SECOND,second);
		}
		if(millisecond>-1){
			ca.set(Calendar.MILLISECOND,millisecond);
		}
		return ca.getTime();
	}
	/**
	 * 对一个时间进行加减操作
	 * @param time
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date time,int field,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(field,amount);
		return ca.getTime();
	}
	public static Date addMonth(Date time,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.MONTH,amount);
		return ca.getTime();
	}
	public static Date addDay(Date time,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.DAY_OF_YEAR,amount);
		return ca.getTime();
	}
	public static Date addHour(Date time,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.HOUR_OF_DAY,amount);
		return ca.getTime();
	}
	public static Date addMinute(Date time,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.MINUTE,amount);
		return ca.getTime();
	}
	public static Date addSecond(Date time,int amount){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.SECOND,amount);
		return ca.getTime();
	}
	/**
	 * 设置一个TIME的值
	 * @param time
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date set(Date time,int field,int value){
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.set(field,value);
		return ca.getTime();
	}
	//复制年月日
	public static Date copyYearMonthDayValue(Date time,Date source){
		Calendar ca = Calendar.getInstance();
		ca.setTime(source);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		ca.setTime(time);
		ca.set(Calendar.YEAR,year);
		ca.set(Calendar.MONTH,month);
		ca.set(Calendar.DAY_OF_MONTH,day);
		return ca.getTime();
	}
	/**
	 * 判断两个日期是否是同一天
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean sameDate(Date dateA,Date dateB){
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);
		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);
		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR) && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH) && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 比较两个时间是否相同
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean equals(Date dateA,Date dateB){
		if(dateA==null && dateB==null)return true;
		if(dateA==null || dateB==null)return false;
		return dateA.getTime()==dateB.getTime();
	}
	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginStr 10:00
	 * @param endStr	03:30
	 * @return
	 */
    public static boolean belongCalendar(Date nowTime,String beginStr, String endStr) {
    	StringUtils.isNotEmpty(beginStr);
    	StringUtils.isNotEmpty(endStr);
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
    	Date now,beginTime,endTime;
        try {
            now = df.parse(df.format(nowTime));
            beginTime = df.parse(beginStr);
            endTime = df.parse(endStr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(now);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        return date.after(begin) && date.before(end);
    }
	public static void main(String[] args) throws Exception {
		long t = System.currentTimeMillis();
		
		belongCalendar(new Date(),"10:00","03:30");
		//my(new Date(),"10:00","03:30");
		
		System.out.println(System.currentTimeMillis()-t);
	}
}
