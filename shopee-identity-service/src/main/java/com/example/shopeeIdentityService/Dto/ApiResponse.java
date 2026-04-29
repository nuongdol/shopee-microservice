package com.example.shopeeIdentityService.Dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//the field of json is null, don't appear in json
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T result;
}
