package com.moujitx.metro.server.exception;

import lombok.Getter;

/**
 * Function:
 * Author: MOUJITX
 * Date: 2023/9/18 21:26
 */
@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
