package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Informa ao Spring que esta classe vai lidar com requisições web
public class HelloController {

    // Mapeia este método para o endereço principal ("/")
    @GetMapping("/")
    public String hello() {
        return "Meu back-end está no ar e funcionando!";
    }
}