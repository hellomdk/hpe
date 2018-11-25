package com.hpe.tf.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class ToolUtil {

	/**
	 * 截取字符串 ,i为固定字符串。
	 * @param str
	 * @return
	 */
	public static List substr(String str,int i){
		List arrys = new ArrayList();
		
		return null;
	}
	
	
	/**
	 * 字符串 非空判断
	 * @return
	 */
	public static boolean isNotNull(String str){
		if(str == null ){
			return false;
		}
		if(str.trim().equals("")){
			return false;
		}
		return true;
	}
	/**
	 * 集合非空 判断
	 * @return
	 */
	public static boolean isNotNull(List list){
		if(list == null){
			return false;
		}
		if(list.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	/*//2017-08-08新增
	public static String getCurrentTime(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	*/
	
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}

	public static Date getDate(String date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 根据毫秒计算 天和小时及秒
	 * @param diff
	 * @return
	 */
	public static String getDateDif(long diff){
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}
	
	public static String getDateDif(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	/**
	 * 
	 * @param endDate 截止日期
	 * @param nowDate 当前日期
	 * @return
	 */
	public static Map<String,Long> getDayHourSe(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    Map<String,Long> map = new HashMap<String, Long>();
	    map.put("d", day);
	    map.put("h", hour);
	    map.put("m", min);
	    map.put("s", sec);
	    return map;
	}

	/**
	 * 计算天，小时，分钟，秒 ，封装成一个map对象
	 * @param diff 毫秒
	 * @return
	 */
	public static Map<String,Long> getDayHourSe(long diff){
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    Map<String,Long> map = new HashMap<String, Long>();
	    map.put("d", day);
	    map.put("h", hour);
	    map.put("m", min);
	    map.put("s", sec);
	    return map;
	}
	public final static String LOGINUSER = "loginUser";

	public static String arraytoString(String[] array) {
		StringBuffer s = new StringBuffer();
		if (null == array)
			return s.toString();
		for (int i = 0; i < array.length; i++) {
			s.append(array[i]);
			if (i != array.length - 1)
				s.append(",");
		}
		return s.toString();
	}
	
	/**
	 * 2017-08-08
	 * 三个字符串数组转换成字符串
	 * @param array1
	 * @param array2
	 * @param array3
	 * @return
	 */
	public static String threeArraytoString(String[] array1,String[] array2,String[] array3) {
		StringBuffer s = new StringBuffer();
//		if (null == array1 || null == array2 || null == array3)
//			return s.toString();
		//第一个字符串数组
		if(array1 != null){
		for (int i = 0; i < array1.length; i++) {
			s.append(array1[i]);
			if (i != array1.length - 1)
				s.append(",");
			else s.append(";");
		}
		}
		//第二个字符串数组
		if(array2 != null){
		for (int i = 0; i < array2.length; i++) {
			s.append(array2[i]);
			if (i != array2.length - 1)
				s.append(",");
			else s.append(";");
		}
		}
		if(array3 != null){
		//第三个字符串数组
		for (int i = 0; i < array3.length; i++) {
			s.append(array3[i]);
			if (i != array3.length - 1)
				s.append(",");
		}
		}
		return s.toString();
	}
	
	
	/**
	 * 转化为UNICODE编码。  // xxxServlet?method=show&name=张三&deptName=开发
	 * @param value
	 * @return
	 */
	public static String encode(String value){
		String newname = null;
		try {
			newname = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newname;
	}
	
	public static String decode(String value){
		String newName=null;
		try {
			newName = URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newName;
	}
	/**
	 * 删除字符串中的特殊字符(~和@@@)
	 * @param str
	 */
	public static String delSpecialChar(String str){
		//int num=0;
		while(true){
			//num++;
			str = str.replaceAll("@@@", "").replaceAll("~","").replaceAll("/", "");
			if(!str.contains("@@@")&&!str.contains("/")&&!str.contains("~"))
				break;
		}
		//////////.println("num:"+num);
		return str;
	}
	
	/**
	 * 判断字符串是否包含除数字以外的其他字符, 非数字。
	 * @param args
	 */
	public static boolean containsOtherChar(String str){
		//if(str.length() != str.replaceAll("[^0-9]","").length()){
		//  /^[0-9]+([.]{1}[0-9]+){0,1}$/   "^[0-9]+([.]{1}[0-9]+){0,1}"
		if(!str.matches("^[0-9]+([.]{1}[0-9]+){0,1}")){	
			return true;
		}else{
			return false;
		}
	}
	public static void main (String [] args){
		/*String s = "Great Britain,the dominant industrial and maritime power of the 19th century,played a leading role in developing parliamentary democracy and in advancing literature and science.At its zenith,the British Empire stretched over one-fourth of the earth's surface.The first half of the 20th century saw the UK's strength seriously depleted in two World Wars.The second half witnessed the dismantling of the Empire and the UK rebuilding itself into a modern and prosperous European nation.As one of five permanent members of the UN Security Council,a founding member of NATO,and of the Commonwealth,the UK pursues a global approach to foreign policy; it currently is weighing the degree of its integration with continental Europe.A member of the EU,it chose to remain outside the Economic and Monetary Union for the time being.Constitutional reform is also a significant issue in the UK.The Scottish Parliament,the National Assembly for Wales,and the Northern Ireland Assembly were established in 1999,but the latter is suspended due to wrangling over the peace process.";
		substr(s, 0);
		String time = getCurrentTime();
		////////.println(time);*/
		
		
		String name = "1@/@/~~23@/@@~648///";
		////////.println(name);
		name = delSpecialChar(name);
		////////.println(name);
		String[] array1 = {"123"};
		String[] array2 = {"1"};
		String[] array3 = null;
		StringBuffer s = new StringBuffer();
		//第一个字符串数组
		for (int i = 0; i < array1.length; i++) {
			s.append(array1[i]);
			if (i != array1.length - 1)
				s.append(",");
			else s.append(";");
		}
		//第二个字符串数组
		for (int i = 0; i < array2.length; i++) {
			s.append(array2[i]);
			if (i != array2.length - 1)
				s.append(",");
			else s.append(";");
		}
		//第三个字符串数组
//		for (int i = 0; i < array3.length; i++) {
//			s.append(array3[i]);
//			if (i != array3.length - 1)
//				s.append(",");
//		}
		
		String num = "4.5.52";
		System.out.println("===="+ num.matches("^[0-9]+([.]{1}[0-9]+){0,1}"));
		
	}
	
	
}
