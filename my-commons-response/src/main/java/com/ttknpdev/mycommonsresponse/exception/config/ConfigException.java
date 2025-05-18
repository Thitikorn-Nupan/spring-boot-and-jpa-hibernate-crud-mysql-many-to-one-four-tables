package com.ttknpdev.mycommonsresponse.exception.config;

import com.ttknpdev.mycommonsresponse.common.Constants;
import com.ttknpdev.mycommonsresponse.enums.StatusDetails;
import com.ttknpdev.mycommonsresponse.exception.handler.NotAllowed;
import com.ttknpdev.mycommonsresponse.exception.entity.Warning;
import com.ttknpdev.mycommonsresponse.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice No need this class when we use other modules
public class ConfigException {
    // @ExceptionHandler(NotAllowed.class) No need this class when we use other modules
    /* I used knowledge in the past
    *  trick , you should think that's just beans
    *  So you can creat them
    *  and use them with any method from other class
    *  in top line method you just use annotations about your beans */
    public static ResponseEntity<ResponseObject> handlerNotAllowed(NotAllowed notAllowed) {
/*        Warning warning = new Warning();
        warning.setStatus(Constants.STATUS_UN_ACCEPT);
        warning.setCode(Constants.STATUS_UN_ACCEPT_CODE);
        warning.setCause(notAllowed.getCurrentCourse());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(warning);*/
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE) // work
                .body(ResponseObject.<String>builder()
                        .code(StatusDetails.unacceptable.toString())
                        .status(Constants.STATUS_UN_ACCEPT)
                        .object("cause of error : "+notAllowed.getMessage())
                        .build());
    }
}
