package com.zyc.datasettagger.controller.component;

import com.zyc.common.enums.ReturnCode;
import com.zyc.common.model.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyc
 * @version 1.0
 */
@Controller
public class NotFoundErrorController extends AbstractErrorController {

    public NotFoundErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ResponseData<String> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return ResponseData.generate(ReturnCode.DATA_NOT_FOUND, status.toString());
    }
}
