// 优化后的完整代码
package com.example.supermarket.exception;

import com.example.supermarket.pojo.entity.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Tag(name = "全局异常处理", description = "全局异常处理接口")
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数异常：{}", e.getMessage());
        return Result.error("参数错误：" + e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleArithmeticException(ArithmeticException e) {
        log.error("算术异常：{}", e.getMessage());
        return Result.error("计算错误：" + e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleNullPointerException(NullPointerException e) {
        log.error("空指针异常：{}", e.getMessage());
        return Result.error("空指针错误：" + e.getMessage() + "，请检查数据");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        log.error("数组越界异常：{}", e.getMessage());
        return Result.error("索引越界：" + e.getMessage() + "，请检查输入范围");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleClassCastException(ClassCastException e) {
        log.error("类型转换异常：{}", e.getMessage());
        return Result.error("类型转换失败：" + e.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleNumberFormatException(NumberFormatException e) {
        log.error("数字格式异常：{}", e.getMessage());
        return Result.error("数字格式错误：" + e.getMessage() + "，请输入有效的数字");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("数据完整性违规：{}", e.getMessage());
        String message = e.getMessage();
        
        if (message.contains("Duplicate entry")) {
            int start = message.indexOf("'");
            int end = message.lastIndexOf("'");
            if (start != -1 && end > start) {
                String duplicateValue = message.substring(start + 1, end);
                String keyPart = message.substring(message.lastIndexOf("for key ") + 9);
                String fieldName = keyPart.replace("'", "").trim();
                
                if ("username".equals(fieldName) || "user_name".equals(fieldName)) {
                    return Result.error("用户名冲突：" + duplicateValue + " 已存在，原因：用户名必须唯一");
                } else if ("phone".equals(fieldName)) {
                    return Result.error("手机号冲突：" + duplicateValue + " 已注册，原因：一个手机号只能注册一次");
                } else if ("card".equals(fieldName)) {
                    return Result.error("卡号冲突：" + duplicateValue + " 已存在，原因：卡号必须唯一");
                } else if ("name".equals(fieldName)) {
                    return Result.error("姓名重复：" + duplicateValue + " 已有记录，原因：不允许同名");
                } else {
                    return Result.error("唯一性冲突：" + duplicateValue + " 重复，原因：该字段要求唯一");
                }
            }
            return Result.error("数据重复：" + e.getMessage());
        } else if (message.contains("foreign key")) {
            return Result.error("外键约束失败：引用的数据不存在");
        } else if (message.contains("NOT NULL")) {
            // 提取字段名
            String fieldName = message;
            if (message.contains("Column")) {
                int colStart = message.indexOf("Column '") + 8;
                int colEnd = message.indexOf("' cannot be null");
                if (colStart != -1 && colEnd > colStart) {
                    fieldName = message.substring(colStart, colEnd);
                }
            }
            return Result.error("必填项为空：" + fieldName + " 不能为空");
        }
        return Result.error("数据验证失败：" + e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("主键冲突：{}", e.getMessage());
        return Result.error("主键冲突：" + e.getMessage() + "，原因：该记录已存在");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.error("数据未找到：{}", e.getMessage());
        return Result.error("数据不存在：" + e.getMessage() + "，原因：查询的 ID 在数据库中找不到");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("JSON解析异常：{}", e.getMessage());
        String message = e.getMessage();
        
        // 处理日期时间格式错误
        if (message.contains("LocalDateTime") || message.contains("LocalDate") || message.contains("LocalTime")) {
            return Result.error("日期格式错误：请使用正确的日期格式，如 2025-12-01 12:34:56");
        }
        
        // 处理其他JSON解析错误
        return Result.error("请求参数格式错误：请检查输入数据格式");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证异常：{}", e.getMessage());
        return Result.error("参数验证失败：" + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常：{} - {}", e.getClass().getSimpleName(), e.getMessage(), e);
        return Result.error("运行时错误：" + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception e) {
        log.error("系统异常：{} - {}", e.getClass().getSimpleName(), e.getMessage(), e);
        return Result.error("系统错误：" + e.getMessage());
    }
}