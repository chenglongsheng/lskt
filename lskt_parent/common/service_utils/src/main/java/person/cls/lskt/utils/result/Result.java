package person.cls.lskt.utils.result;

import lombok.Data;

/**
 * 统一返回结果
 *
 * @param <T> 返回的结果类型
 */
@Data
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回状态信息（成功 失败）
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public Result() {
    }

    /**
     * 成功的方法
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(Code.SUCCESS.getCode());
        result.setMessage(Code.SUCCESS.getMsg());
        return result;
    }

    /**
     * 失败的方法
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(Code.FAIL.getCode());
        result.setMessage(Code.FAIL.getMsg());
        return result;
    }

    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}
