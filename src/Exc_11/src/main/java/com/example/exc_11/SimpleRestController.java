package com.example.exc_11;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SimpleRestController {
    @GetMapping("/example")
    public String example() {
        return "Hello User !! " + new Date();
    }

    @GetMapping("/classlevel")
    public String serviceUnavailable() {
        return "The HTTP Status will be SERVICE_UNAVAILABLE (CODE 503)\n";
    }

    @GetMapping("/methodlevel")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public String ok() {
        return "Class Level HTTP Status Overriden. The HTTP Status will be OK (CODE 200)\n";
    }
}
