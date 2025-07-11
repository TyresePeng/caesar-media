package org.caesar.media.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.enums.ErrorCode;
import org.caesar.media.exception.BusinessException;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 业务断言工具类
 * 
 * @author peng.guo
 */
@Slf4j
@UtilityClass
public class BusinessAssert {

    /**
     * 断言对象非空
     */
    public static void notNull(Object object, ErrorCode errorCode) {
        if (Objects.isNull(object)) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言对象非空
     */
    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言字符串非空
     */
    public static void notBlank(String str, ErrorCode errorCode) {
        if (str == null || str.trim().isEmpty()) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言字符串非空
     */
    public static void notBlank(String str, String message) {
        if (str == null || str.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言集合非空
     */
    public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言集合非空
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言表达式为真
     */
    public static void isTrue(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言表达式为真
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言表达式为假
     */
    public static void isFalse(boolean expression, ErrorCode errorCode) {
        if (expression) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言表达式为假
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言数字在指定范围内
     */
    public static void inRange(Number value, Number min, Number max, String message) {
        if (value == null || value.doubleValue() < min.doubleValue() || value.doubleValue() > max.doubleValue()) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, message);
        }
    }

    /**
     * 断言状态
     */
    public static void state(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 断言状态
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, message);
        }
    }

    /**
     * 断言状态并提供懒加载消息
     */
    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, messageSupplier.get());
        }
    }
}