package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    @GetMapping()
    String showFormulario() {
        return "formulario";
    }
}
