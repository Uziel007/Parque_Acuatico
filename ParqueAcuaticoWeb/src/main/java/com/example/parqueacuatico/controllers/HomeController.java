package com.example.parqueacuatico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarPaginaInicio(Model model) {
        model.addAttribute("titulo", "Bienvenidos al Parque Acuático");
        return "index"; // Retorna la vista index.html
    }
}
