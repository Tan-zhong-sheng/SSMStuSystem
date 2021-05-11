package Mytool;

import java.util.HashMap;
import java.util.Map;

public class FaceSearch {
	 public static String search(String photo) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
	        try {
	        	byte[] bytes1 = FileUtil.readFileByBytes(photo);
	        	String image1 = Base64Util.encode(bytes1);
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", image1);
	            map.put("liveness_control", "LOW");
	            map.put("group_id_list", "teacher1");
	            map.put("image_type", "BASE64");
	            map.put("quality_control", "LOW");

	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken =  token.getAuth(token.ak,token.sk);

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            System.out.println(result);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	  /*  public static void main(String[] args) {
	        FaceSearch.search();
	    }*/
}
