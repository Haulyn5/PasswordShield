package cn.adminzero.passwordshield_demo0.FaceManger.ResultClass;

//log_id	是	uint64	请求标识码，随机数，唯一
//        face_list	是	array	人脸列表
//        + face_token	是	string	人脸图片的唯一标识
//        +ctime	是	string	人脸创建时间

import java.lang.reflect.Array;
import java.util.List;

public class getFaceListResult {
    private int error_code;
    private String error_msg;
    private String log_id;
    private String timestamp;
    private int cached;

    private FaceList result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public FaceList getResult() {
        return result;
    }

    public void setResult(FaceList result) {
        this.result = result;
    }
}
