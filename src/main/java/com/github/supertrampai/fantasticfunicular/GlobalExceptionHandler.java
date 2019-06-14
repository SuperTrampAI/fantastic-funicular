
package com.github.supertrampai.fantasticfunicular;


import com.github.supertrampai.fantasticfunicular.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Component
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Resp handle(ConstraintViolationException exception) {
        StringBuffer msg = new StringBuffer();
        if (exception instanceof ConstraintViolationException) {
            for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
                msg.append(e.getPropertyPath()).append(e.getMessage()).append(";");
            }
            return new Resp(1, msg.toString());
        }
        return new Resp(1, "出错了");
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Resp handle(MissingServletRequestParameterException exception) {
        return new Resp(1, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Resp methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return errorMessage(fieldError);
            }
            return objectError.getDefaultMessage();
        }).reduce((a, b) -> a + "; " + b).orElse("");
        return new Resp(1, errorMsg);
    }


    private String errorMessage(FieldError fieldError) {
        String message = fieldError.getDefaultMessage();
        if ("不能为空".equals(message)) {
            message = fieldError.getField() + message;
        }
        if ("不能为null".equals(message)) {
            message = fieldError.getField() + message;
        }
        return message;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Resp httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return new Resp(1, "表单数据填写有误：" + e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Resp<?> httpMessageNotReadableExceptionHandler(ServiceException e) {
        logger.error("Service调用异常", e);
        return new Resp<>(2, e.getMessage());
    }

    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    //上传文件异常处理

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

}
