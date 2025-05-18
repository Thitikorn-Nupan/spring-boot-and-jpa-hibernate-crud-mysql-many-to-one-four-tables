package com.ttknpdev.mycommonsresponse.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder // for build object in one (;)
public  class ResponseObject<T> { // generic can use with sample class
    private Short status;
    private String code;
    private T object;

    public ResponseObject(Short status, String code, T object) {
        this.status = status;
        this.code = code;
        this.object = object;
    }

}
