package com.wdxxl.wechat.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WeChatUtils {
	//http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E5%85%A5%E6%8C%87%E5%8D%97
	public static boolean checkSignature(String signature,String timestamp,String nonce,String token){
		boolean result = false;
		String[] temp = {token, timestamp, nonce};
		Arrays.sort(temp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			sb.append(temp[i]);
		}
		String tempstr = sb.toString();
		String pwd = SHA1(tempstr);
		if (signature.equals(pwd)) {
			result = true;
		}
		return result;
	}
	private static String SHA1(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5
			byte[] bt = inStr.getBytes();
			md.update(bt);      
			outStr = byteToStr(md.digest());  		//返回的是byet[]，要转化为String存储比较方便
		}catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}
	// 将字节转换为十六进制字符串  
    private static String byteToHexStr(byte ib) {  
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};  
        char[] ob = new char[2];  
        ob[0] = Digit[(ib >>> 4) & 0X0F];  
        ob[1] = Digit[ib & 0X0F];  
        String s = new String(ob);  
        return s;  
    }  
	// 将字节数组转换为十六进制字符串  
    private static String byteToStr(byte[] bytearray) {  
        String strDigest = "";  
        for (int i = 0; i < bytearray.length; i++) {  
            strDigest += byteToHexStr(bytearray[i]);  
        }  
        return strDigest.toLowerCase();  
    }  
    
    
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        @SuppressWarnings("unchecked")  
        List<Element> elementList = root.elements(); 
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
        // 释放资源  
        inputStream.close();  
        inputStream = null;
        return map;  
	 }  
    
    
    
    
}
