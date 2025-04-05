package web.petbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Operation(summary = "测试接口：Hello World")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
