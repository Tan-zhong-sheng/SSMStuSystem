package Mytool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class PhoneCode1 {
	private static final String QUERY_PATH="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    private static final String ACCOUNT_SID="6a29315b6fba4074add4b2b766a5d276";
    private static final String AUTH_TOKEN="d7045cf9c1e145a781b2c80a5fe312d8";


    //根据相应的手机号发送验证码
    public static Map<String, Object> CheckPhone1(String phone){
    	Map<String, Object> m = new HashMap<String, Object>();
        String rod=Mytool.GetCheckCode();
        String timestamp=getTimestamp();
        String sig=getMD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);
        String tamp="【顺丰科技】您的验证码为"+rod+"，请于1分钟内正确输入，如非本人操作，请忽略此短信。";//这里一定要与新建模板中的短信内容一致，一个空格都不能多，否者短信打死都发不过去哦
        OutputStreamWriter out=null;
        BufferedReader br=null;
        StringBuilder result=new StringBuilder();
        try {
            URL url=new URL(QUERY_PATH);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);//设置是否允许数据写入
            connection.setDoOutput(true);//设置是否允许参数数据输出
            connection.setConnectTimeout(35000);//设置链接响应时间
            connection.setReadTimeout(10000);//设置参数读取时间
            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");          
            //提交请求
            out=new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            String args=getQueryArgs(ACCOUNT_SID, tamp, phone, timestamp, sig, "JSON");
            out.write(args);
            out.flush();
            //读取返回参数

            br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String temp="";
            while((temp=br.readLine())!=null){
                result.append(temp);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        	m.put("code", rod);
        	m.put("result", result);
      /*  JSONObject json=new JSONObject().fromObject(result);
        String respCode=json.getString("respCode");
        String defaultRespCode="00000";
        if(defaultRespCode.equals(respCode)){
             //return rod;
        	
        }else{
            //return defaultRespCode;         
        }
		return json;*/
        return m;	
    }
    //定义一个请求参数拼接方法
    public static String getQueryArgs(String accountSid,String smsContent,String to,String timestamp,String sig,String respDataType){
        return "accountSid="+accountSid+"&smsContent="+smsContent+"&to="+to+"&timestamp="+timestamp+"&sig="+sig+"&respDataType="+respDataType;
    }
    //获取时间戳
    public static String getTimestamp(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
   //sing签名
    public static String getMD5(String sid,String token,String timestamp){

        StringBuilder result=new StringBuilder();
        String source=sid+token+timestamp;
        //获取某个类的实例
                try {                   
                   MessageDigest digest=MessageDigest.getInstance("MD5");
                   //要进行加密的东西
                   byte[] bytes=digest.digest(source.getBytes());
                   for(byte b:bytes){
                       String hex=Integer.toHexString(b&0xff);
                       if(hex.length()==1){
                           result.append("0"+hex);
                       }else{
                           result.append(hex);
                       }
                   }
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 


        return result.toString();
    }
}
