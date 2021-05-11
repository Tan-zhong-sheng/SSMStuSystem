package Mytool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


public class PhoneCode {
	public static Map<String, String> CheckPhone(String phone) throws HttpException, IOException{
		HashMap<String,String> m = new HashMap<String,String>();
		HttpClient  h= new HttpClient();
		PostMethod post = new PostMethod("http://gbk.api.smschinese.cn/");
		String code = Mytool.GetCheckCode();
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
		NameValuePair[] data = new NameValuePair[]{
				new NameValuePair("Uid", "kingofkings"),
				new NameValuePair("Key", "d41d8cd98f00b204e980"), 
				new NameValuePair("smsMob",phone),
				new NameValuePair("smsText"," 验证码:"+code+"，发送 ")};
	    post.setRequestBody(data);
	    h.executeMethod(post);
	    //Header[] headers = post.getResponseHeaders();  
	    int statusCode = post.getStatusCode();  
	    System.out.println("statusCode:"+statusCode); 
	    String result = new String(post.getResponseBodyAsString().getBytes("gbk"));  
	    System.out.println(result);
	    m.put("result", result);  
	    m.put("code", code);
	    post.releaseConnection();
	    return m;
	}

}
