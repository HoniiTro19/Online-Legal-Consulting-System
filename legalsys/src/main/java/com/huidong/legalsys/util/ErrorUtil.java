package com.huidong.legalsys.util;

import com.huidong.legalsys.domain.Error;

public class ErrorUtil {

    public static Error success(){
        Error error = new Error();
        error.setCode(0);
        error.setMsg("成功");
        return error;
    }

    public static Error error(Integer code, String msg) {
        Error error = new Error();
        error.setCode(code);
        error.setMsg(msg);
        return error;
    }
}
