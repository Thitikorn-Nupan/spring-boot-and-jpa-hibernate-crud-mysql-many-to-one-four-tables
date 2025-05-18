package com.ttknpdev.mycommonsresponse.common;

import com.ttknpdev.mycommonsresponse.enums.StatusDetails;
import org.springframework.http.HttpStatus;

public class ConstantsResponse {
    public static final Short STATUS_CREATED = (short) HttpStatus.CREATED.value();
    public static final String STRING_CREATED = StatusDetails.created.toString();
    public static final Short STATUS_ACCEPTED = (short) HttpStatus.ACCEPTED.value();
    public static final String STRING_ACCEPTED = StatusDetails.accepted.toString();
    public static final Short STATUS_OK = (short) HttpStatus.OK.value();
    public static final String STRING_OK = StatusDetails.ok.toString();

    // bad status in below
    public static final Short STATUS_UNACCEPTABLE = (short) HttpStatus.NOT_ACCEPTABLE.value();
    public static final String STRING_UNACCEPTABLE = StatusDetails.unacceptable.toString();
    public static final Short STATUS_UNSOURCED = (short) HttpStatus.BAD_REQUEST.value();
    public static final String STRING_UNSOURCED = StatusDetails.unsourced.toString();
}
