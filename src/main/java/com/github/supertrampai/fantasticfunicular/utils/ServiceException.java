/**
 * Copyright © 2017 - 2018 交艺网.All Rights Reserved.
 */
package com.github.supertrampai.fantasticfunicular.utils;

/**
 * Service调用异常
 *
 * @author xinwe
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }
}
