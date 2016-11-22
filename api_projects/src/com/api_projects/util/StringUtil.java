package com.api_projects.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String LETTER = "abcdefghijklmnopqrstuvwxyz";

	private StringUtil() {

	}

	private static int getRandom(int count) {
		return (int) Math.round(Math.random() * (count));
	}

	/**
	 * 获取随机x位数的随机小写字母串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		int len = LETTER.length();
		for (int i = 0; i < length; i++) {
			sb.append(LETTER.charAt(getRandom(len - 1)));
		}
		return sb.toString();
	}
	
	/**
	 * 获取UID
	 * @return
	 */
	public static String getUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取不带符号的uid
	 * @return
	 */
	public static String UID2(){
		String uid = UUID.randomUUID().toString();
		return uid.replace("-", "");
	}

	/**
	 * 为空返回true
	 * @param str
	 * @return
	 */
	public static boolean isEmptyStr(String str) {
		if (str != null && !"".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String newReplace(String str1, String regex,
			String replacement) {
		if (str1 == null) {
			return "";
		} else {
			return str1.replace(regex, replacement);
		}
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isDecimal(String str) {
		Pattern pattern = Pattern.compile("\\d+\\.\\d+$|^\\d+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// 获取有效位数为n的double
	public static double getDNum(double d, int n) {
		// 四舍五入
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 取当前时间 时分秒
	 * 
	 * @return
	 */
	public static String Now() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		return dateFormat.format(now);
	}

	public static String uuid(String time) {
		return UUID.randomUUID().toString().substring(0, 6).replace("-", "")
				+ time;
	}

	public static char Temp() {
		int random = (int) Math.round(Math.random() * 25 + 97);
		char temp = (char) random;
		return temp;
	}

	/**
	 * 获取随机金额 maxMoney剩余金额,bonuseNum 总数,outnum 已经发出的数额,bonuseType 类型(0:平均,1:随机)
	 * 最小0.01,最大一半乘以随机
	 */
	public static double getRandomMoney(double maxMoney, int bonuseNum,
			int outnum, int bonuseType) {
		double returnVal = 0.01;
		int resNum = bonuseNum - outnum;
		if (resNum == 1) {
			returnVal = maxMoney;
		} else {
			if (bonuseType == 0) {
				returnVal = maxMoney / resNum;
			} else {
				returnVal = maxMoney * Math.random() / 2;
				if (returnVal < 0.01) {
					returnVal = 0.01;
				}
			}
		}
		System.out.println(returnVal);
		return getDNum(returnVal, 2);
	}

	// 生成买单编号
	public static String getPayNumber(int payId) {
		String r = payId
				+ "UP"
				+ SystemDateFormatUtil.formatDate(new Date(),
						SystemDateFormatUtil.FORMAT_DATETIME_SECOND);
		return r;
	}

	// 生成订单编号 12
	public static String getOrderNumber(int oid) {
		String t = new Date().getTime() + "";
		t = t.substring(7);
		String o = oid + "";
		String r = getRandomString(1) + (int) (Math.random() * 1000000);
		return (o + r + t).substring(0, 12);
	}

	// 获取len的随机数
	public static String getStringRandom(int len) {
		int num = 1;
		for (int i = 1; i < len; i++) {
			num = num * 10;
		}
		num = (int) ((Math.random() * 9 + 1) * num);
		return num + "";
	}

	/**
	 * 随机生成12位字符串 (只含有大小写字母、数字)
	 */
	public static String random(int length) {// 传入的字符串的长度
		StringBuilder builder = new StringBuilder(length);
		// 返回以纳秒为单位的当前时间。
		long r1 = System.nanoTime() / 1000000L; // 7位
		for (int i = 0; i < length; i++) {

			int r = (int) (Math.random() * 3);
			int rn1 = (int) (48 + Math.random() * 10);
			int rn2 = (int) (65 + Math.random() * 26);
			int rn3 = (int) (97 + Math.random() * 26);

			switch (r) {
			case 0:
				builder.append((char) rn1);
				break;
			case 1:
				builder.append((char) rn2);
				break;
			case 2:
				builder.append((char) rn3);
				break;
			}
		}
		return builder.toString() + r1;
	}

	/**
	 * 获取12位数字 券码
	 * 
	 * @return
	 */
	public static String getVoucherNumber() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String rs = String.valueOf((System.nanoTime() + 1));
		StringBuffer sb = new StringBuffer();
		sb.append(dateFormat.format(now));
		sb.append(rs.substring(rs.length() - 6, rs.length()));
		return sb.toString();
	}

	public static String returnNotNull(String value) {
		return value == null ? "" : value;
	}

	public static String[] returnXY(String value) {
		if (value.indexOf(",") < 0) {
			return null;
		} else {
			String[] xy = value.split(",");
			if (StringUtil.isDecimal(xy[0]) && StringUtil.isDecimal(xy[1])) {
				return xy;
			} else {
				return null;
			}
		}
	}

}
