package cn.edu.zucc.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private Object data;
    private Integer status;
    private String message;

    public Result(Integer status) {
        this.status = status;
    }

    public Result(Integer status, String msg) {
        this.status = status;
        this.message = msg;
    }

    public static Result ok(Object o, Integer status) {
        Result r = new Result(status);
        r.setData(o);
        return r;
    }

    public static Result ok(Integer status, String msg) {
        Result r = new Result(status);
        r.setMessage(msg);
        return r;
    }

    public static Result ok(Object o, Integer status, String msg) {
        Result r = new Result(status);
        r.setData(o);
        r.setMessage(msg);
        return r;
    }

    public static Result ok(Integer status) {
        return new Result(status);
    }

    public static Result fail(Integer status, String msg) {
        return new Result(status, msg);
    }

    public static Result fail(Integer status) {
        return new Result(status);
    }
}
