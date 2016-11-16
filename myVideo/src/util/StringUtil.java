package util;

public class StringUtil {
	public static boolean isEmptyStr(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
}