package com.hpe.tf.utils;

import java.util.Random;

import org.apache.log4j.Logger;

/**   
 * @ClassName:  SnUtil   
 * @Description:TODO(交易流水号生成工具)   
 * @author: lilong
 * @date:   2018年3月29日 下午3:53:55       
 */  
public class OrderSnUtil {
	
	private static final Logger logger = Logger.getLogger(OrderSnUtil.class);
	/**
	 * 方法描述	：时间戳毫秒值+登陆者ID后两位+一位随机数
	 * 
	 */
	public static String createOrderSn(int userId){
		String idStr = userId+"";
		String idStr2 = null;
		if(idStr.length() < 3 ){
			idStr2 = idStr;
		}else{
			idStr2 = idStr.substring(idStr.length() - 2,2);
		}
		logger.debug("订单字符串ID参数："+idStr2);
		Random ran =new Random();
		int ranNext1 = ran.nextInt(9);
		logger.debug("订单字符串随机数参数1："+ranNext1);
		int ranNext2 = ran.nextInt(9);
		logger.debug("订单字符串随机数参数2："+ranNext2);
	    Long time = System.currentTimeMillis();
	    String timeStr = time.toString();
	    logger.debug("订单字符串时间参数："+timeStr);
	    String orderSn = timeStr+ranNext1+idStr2+ranNext2;
	    logger.info("订单号*******-orderSn:"+orderSn);
		return orderSn;
	}
}





