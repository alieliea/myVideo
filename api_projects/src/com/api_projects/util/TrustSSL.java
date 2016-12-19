package com.api_projects.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Post 的4种数据请求
 * 1.application/x-www-form-urlencoded  浏览器的原生 form 表单，如果不设置 enctype 属性，那么最终就会以 application/x-www-form-urlencoded 方式提交数据
 * 2.multipart/form-data 				我们使用表单上传文件时，必须让 form 的 enctyped 等于这个值
 * 3.application/json 					消息主体是序列化后的 JSON 字符串
 * 4.text/xml							使用 HTTP 作为传输协议，XML 作为编码方式的远程调用规范
 * @author Administrator
 *
 */



public class TrustSSL {
    private static class TrustAnyTrustManager implements X509TrustManager {
    
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
    
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    
    /**
	 * http请求
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static String requestUrl(String url, String params)
			throws IOException {
		HttpURLConnection conn;
		try {
			URL requestUrl = new URL(url);
			conn = (HttpURLConnection) requestUrl.openConnection();
		} catch (MalformedURLException e) {
			return e.getMessage();
		}
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(params.getBytes("utf-8"));
        out.flush();
        out.close();
        
		String line;
		BufferedReader bufferedReader;
		StringBuilder sb = new StringBuilder();
		InputStreamReader streamReader = null;
		try {
			streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
		} catch (IOException e) {
			/*
			Boolean ret2 = true;
			if (ret2) {
				return e.getMessage();
			}
			*/
			streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
		} finally {
			if (streamReader != null) {
				bufferedReader = new BufferedReader(streamReader);
				sb = new StringBuilder();
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 参数编码
	 * @param data
	 * @return 
	 */
	public static String httpBuildQuery(HashMap<String, String> data) {
		String ret = "&";
		String k, v;
		if(data!=null){
			Iterator<String> iterator = data.keySet().iterator();
			while (iterator.hasNext()) {
				k = iterator.next();
				v = data.get(k);
				try {
					ret += URLEncoder.encode(k, "utf8") + "=" + URLEncoder.encode(v, "utf8");
				} catch (UnsupportedEncodingException e) {
				}
				ret += "&";
			}			
		}
//		System.out.println(ret.substring(0, ret.length() - 1));
		return ret.substring(0, ret.length() - 1);
	}
    

    public static String sengHTTPSGet(String url,HashMap<String, String> data,String params) throws Exception {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
        URL console = new URL(url+httpBuildQuery(data));
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
       // conn.connect();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(params.getBytes("utf-8"));
        // 刷新、关闭
        out.flush();
        out.close();
		String line;
		BufferedReader bufferedReader;
		StringBuilder sb = new StringBuilder();
		InputStreamReader streamReader = null;
		try {
			streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
		} catch (IOException e) {
			streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
		} finally {
			if (streamReader != null) {
				bufferedReader = new BufferedReader(streamReader);
				sb = new StringBuilder();
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		return sb.toString();
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost_application(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
    /** 
     * 发送HttpPost请求  json数据传输
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String httpPost(String strURL, String params) {  
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params); 
            
            out.flush();  
            out.close();  
            String line;
    		BufferedReader bufferedReader;
    		StringBuilder sb = new StringBuilder();
    		InputStreamReader streamReader = null;
    		try {
    			streamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
    		} catch (IOException e) {
    			streamReader = new InputStreamReader(connection.getErrorStream(), "UTF-8");
    		} finally {
    			if (streamReader != null) {
    				bufferedReader = new BufferedReader(streamReader);
    				sb = new StringBuilder();
    				while ((line = bufferedReader.readLine()) != null) {
    					sb.append(line);
    				}
    			}
    		}
    		return sb.toString();         
        } catch (Exception e) {   
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }  
    
    public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(req_url);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
   
            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
   
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
   
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
   
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
   
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  
    
    public static void main(String[] args) throws Exception {
//		http://www.inroids.com/yongyou/inter_json/user_getcode.do?phone=15380479973
		
		System.out.println();
    }
    	

    
    
    
}