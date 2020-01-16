package pers.dc.ols.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import pers.dc.ols.utils.JSONResult;

import javax.naming.SizeLimitExceededException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JSONResult handleMaxUploadSizeExceededException() {
        return JSONResult.errorMsg("上传文件大小不能超过1MB");
    }
}
