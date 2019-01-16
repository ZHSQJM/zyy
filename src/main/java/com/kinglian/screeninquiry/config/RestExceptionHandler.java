package com.kinglian.screeninquiry.config;
import cn.kinglian.spring.config.LoginException;
import cn.kinglian.spring.config.NullTokenException;
import cn.kinglian.spring.config.RBuilder;
import cn.kinglian.spring.util.R;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一错误拦截返回
 * @author zengfeng
 */
@ControllerAdvice()
@ResponseBody
@Slf4j
public class RestExceptionHandler {

    private static final String RIGHT = "返回正常";

    class Status{
        static final int InternalErrorCode = 0;
        static final String InternalErrorContent = "服务器繁忙";

        static final int MySQLIntegrityConstraintViolationCode = 500;
        static final String MySQLIntegrityConstraintViolationContent = "请检查是否重复添加。";

        static final int OKCode = 200;
        static final String OKContent = "响应正确";

        static final int AuthError = 401;
        static final String AuthErrorContent = "授权失败";

        static final int RequestError = 501;
        static final String RequestErrorContent = "请求方式错误";

        static final int ExpireCode = 402;
        static final String ExpireContent = "登录超时";

        static final int NullTokenCode = 403;
        static final String NullTokenContent = "token为空，请检查token是否正确";

        static final int INTERNAL_ERROR = 500;
        static final String INTERNAL_CONTENT = "服务器繁忙";
    }
    /**
     *  方法请求不允许
     * @param e exception
     * @return R'JSON data
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.InternalErrorCode)
                .setMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R mySQLIntegrityConstraintViolationExceptionHandler(DuplicateKeyException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.MySQLIntegrityConstraintViolationCode)
                .setMessage(Status.InternalErrorContent)
                .build();
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R illegalParamsExceptionHandler(JsonMappingException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.InternalErrorCode)
                .setMessage(Status.InternalErrorContent)
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> R handleException(IllegalArgumentException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.InternalErrorCode)
                .setMessage(e.getMessage())
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ReflectionException.class)
    public <T> R handleException(ReflectionException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.InternalErrorCode)
                .setMessage(Status.InternalErrorContent)
                .build();
    }




    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> R handleException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.RequestError)
                .setMessage(Status.RequestErrorContent)
                .build();
    }


    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R illegalStateHandler(IllegalStateException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.InternalErrorCode)
                .setMessage(Status.InternalErrorContent)
                .build();
    }



    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R LoginExceptionHandler(LoginException e) {

        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.ExpireCode)
                .setMessage(e.getMessage())
                .build();
    }

    @ExceptionHandler(NullTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R nullTokenException(NullTokenException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.NullTokenCode)
                .setMessage(Status.NullTokenContent)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R exception(Exception e) {
        e.printStackTrace();return new RBuilder<T>()
                .setErrorCode(Status.INTERNAL_ERROR)
                .setMessage("内部错误")
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> R iOExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return new RBuilder<T>()
                .setErrorCode(Status.INTERNAL_ERROR)
                .setMessage(Status.INTERNAL_CONTENT)
                .build();
    }

}
