package com.huidong.legalsys.handle;

import com.huidong.legalsys.domain.Error;
import com.huidong.legalsys.exception.*;
import com.huidong.legalsys.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Error handle(Exception exception) {
        if (exception instanceof LegalsysException) {
            LegalsysException legalsysException = (LegalsysException) exception;
            return ErrorUtil.error(legalsysException.getCode(), legalsysException.getMessage());
        }else {
            return ErrorUtil.success();
        }
    }
}