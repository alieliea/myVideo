package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesConfig {
    public static final Locale locale_zh = new Locale("zh", "CN"); 
    
    public static final ResourceBundle resb_zh = ResourceBundle.getBundle("file", locale_zh);
    
    public static String findProVal(String key){
        return resb_zh.getString(key);
    }
    
    public static final String filepath = findProVal("filepath");
    public static final String fileDB = findProVal("fileDB");
}