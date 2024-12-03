package com.dedasp.common.enums;

public enum ResponseCode {

    SUCCESS(200, "Success"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    CUSTOM_ERROR(1001, "Custom Error"),

    //  自定义返回值
    STATEMENT_NOT_UPLOADED(421,"结算书未上传"),
    BILL_NOT_UPLOADED(422,"发票未上传"),
    WORKSTEP_UPDTAE_FAILED(423,"流程步骤更新失败");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
