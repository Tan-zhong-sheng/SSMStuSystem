package Mytool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceVerify {
	public static String faceVerify(String photo,String photo1){
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            byte[] bytes1 = FileUtil.readFileByBytes(photo);
            byte[] bytes2 = FileUtil.readFileByBytes(photo1);
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "NORMAL");
            map1.put("liveness_control", "HIGH");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "NORMAL");
            map2.put("liveness_control", "HIGH");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = token.getAuth(token.ak,token.sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
    	FaceVerify.faceVerify("C:\\Users\\Machenike\\Desktop\\新建文件夹\\2.jpg","C:\\Users\\Machenike\\Desktop\\新建文件夹\\3.jpg");
    }
}
