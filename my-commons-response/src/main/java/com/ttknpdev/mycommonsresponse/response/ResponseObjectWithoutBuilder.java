package com.ttknpdev.mycommonsresponse.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseObjectWithoutBuilder<T> {
    private Short status;
    private String code;
    private T object;

    public ResponseObjectWithoutBuilder(Short status, String code, T object) {
        this.status = status;
        this.code = code;
        this.object = object;
    }
}
