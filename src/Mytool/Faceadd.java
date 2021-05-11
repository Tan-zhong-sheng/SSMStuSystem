package Mytool;

import java.util.HashMap;
import java.util.Map;


public class Faceadd {
	public static String add(String Route,String id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
        	String filePath = "D:\\photo\\"+Route;
        	byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
           // System.out.println(imgStr);
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgStr);
            map.put("group_id", "teacher1");
            map.put("user_id", id);
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = token.getAuth(token.ak, token.sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

/*public static void main(String[] args) {
	System.out.println(Faceadd.add());
}*/

}
