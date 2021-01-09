package com.cemayan.annotation.exceptions;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class MyNullException  extends NestedRuntimeException {

    private final HttpStatus status;
    @Nullable
    private final String reason;

    public HttpStatus getStatus() {
        return this.status;
    }

    public MyNullException(HttpStatus status) {
        this(status, (String) null, (Throwable) null);
    }

    public MyNullException(HttpStatus status, @Nullable String reason) {
        this(status, reason, (Throwable) null);
    }


    public MyNullException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause) {
        super((String) null, cause);
        Assert.notNull(status, "HttpStatus is required");
        this.status = status;
        this.reason = reason;
    }

    @Nullable
    public String getReason() {
        return reason;
    }
}