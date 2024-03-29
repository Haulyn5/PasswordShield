package cn.adminzero.passwordshield_demo0.FaceManger;
import android.os.Environment;

import cn.adminzero.passwordshield_demo0.util.Base64Util;
import cn.adminzero.passwordshield_demo0.util.FileUtil;
import cn.adminzero.passwordshield_demo0.util.HttpUtil;
import cn.adminzero.passwordshield_demo0.util.GsonUtils;

import java.util.*;

import static cn.adminzero.passwordshield_demo0.util.LogUtils.d;

public class Face {

    public static String match(String face_token) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            byte[] bytes1 = FileUtil.readFileByBytes(Environment.getExternalStorageDirectory() + "/DCIM/Camera/"
                    + "temp" + ".jpg");
//            byte[] bytes2 = FileUtil.readFileByBytes("【本地文件2地址】");
            String image1 = Base64Util.encode(bytes1);
//            String image2 = Base64Util.encode(bytes2);
            String image2 = face_token;

            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "HIGH");
            map1.put("liveness_control", "HIGH");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "FACE_TOKEN");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "HIGH");
            map2.put("liveness_control", "HIGH");


            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUser(String group_id, String user_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", user_id);
            map.put("group_id", group_id);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //向人脸库中添加照片
    public static String addFace(String image_base,String user_id,String group_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image_base);
            map.put("group_id", group_id);
            map.put("user_id", user_id);
            map.put("user_info", "UserTest");
            map.put("liveness_control", "HIGH");
            map.put("image_type", "BASE64");
            map.put("quality_control", "HIGH");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            d(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //创建新的用户组
    public static String groupAdd(String group_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", group_id);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取某用户的所有照片信息
    public static String getFaceList(String user_id,  String group_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", user_id);
            map.put("group_id", group_id);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取组列表
    public static String GroupGetlist() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", 0);
            map.put("length", 100);
            String param = GsonUtils.toJson(map);

                // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取指定组中的用户列表
    public static String getUsers(String group_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", group_id);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String search(String image_base,String  group_id,String user_id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image_base);
            map.put("liveness_control", "HIGH");
            map.put("group_id_list", group_id);
            map.put("image_type", "BASE64");
            map.put("quality_control", "HIGH");
            map.put("user_id", user_id);

            String param = GsonUtils.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
