package person.cls.lskt.utils.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum Code {

    SUCCESS(20000, "成功"),
    FAIL(20001, "失败"),
    SERVICE_ERROR(20012, "服务异常"),
    DATA_ERROR(20004, "数据异常"),
    ILLEGAL_REQUEST(20005, "非法请求"),
    REPEAT_SUBMIT(20006, "重复提交"),

    LOGIN_AUTH(20008, "未登陆"),
    PERMISSION(20009, "没有权限"),

    PHONE_CODE_ERROR(20011, "手机验证码错误"),

    MTCLOUD_ERROR(20010, "直播接口异常"),

    COUPON_GET(20020, "优惠券已经领取"),
    COUPON_LIMIT_GET(20021, "优惠券已发放完毕"),

    FILE_UPLOAD_ERROR(21004, "文件上传错误"),
    FILE_DELETE_ERROR(21005, "文件刪除错误"),

    VOD_PLAY_ERROR(20009, "请购买后观看");

    private final Integer code;
    private final String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
