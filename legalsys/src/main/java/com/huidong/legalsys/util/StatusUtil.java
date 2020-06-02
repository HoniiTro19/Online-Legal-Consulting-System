package com.huidong.legalsys.util;

import com.huidong.legalsys.domain.Status;

public class StatusUtil {

    public static Status success(Object object){
        Status status = new Status();
        status.setCode(0);
        status.setMsg("成功");
        status.setData(object);
        return status;
    }

    public static Status success(){
        return success(null);
    }

    public static Status error(Integer code, String msg) {
        Status status = new Status();
        status.setCode(code);
        status.setMsg(msg);
        return status;
    }
}
