package com.example.shopeeIdentityService.Enum.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum GlobalErrorCode {
    // 4xx
    INVALID_REQUEST("400-001", "Yêu cầu không hợp lệ", 400),
    RESOURCE_NOT_FOUND("404-001", "Không tìm thấy tài nguyên", 404),
    USER_ALREADY_EXISTS("409-001", "Người dùng đã tồn tại", 409),

    //5xx
    INTERNAL_SERVER_ERROR("500", "Lỗi hệ thống", 500),
    DATABASE_ERROR("500-001", "Lỗi truy vấn cơ sở dữ liệu", 500-001),
    ;
    private final String code;
    private final String message;
    private final Integer Status;
}
