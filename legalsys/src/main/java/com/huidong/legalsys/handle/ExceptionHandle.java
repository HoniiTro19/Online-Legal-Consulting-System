package com.huidong.legalsys.handle;

import com.huidong.legalsys.domain.Status;
import com.huidong.legalsys.exception.*;
import com.huidong.legalsys.util.StatusUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Status handle(Exception exception) {
        if (exception instanceof LegalsysException) {
            LegalsysException legalsysException = (LegalsysException) exception;
            return StatusUtil.error(legalsysException.getCode(), legalsysException.getMessage());
        }else {
            logger.error("[系统异常]{}", exception);
            return StatusUtil.error(-1, "未知错误");
        }
    }
}