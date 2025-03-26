package com.example.parqueacuatico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompraController {

    @GetMapping("/compra")
    public String mostrarFormularioCompra(Model model) {
        model.addAttribute("titulo", "Realizar Compra");
        return "compra"; // Retorna la vista compra.html
    }

    @PostMapping("/compra")
    public String procesarCompra(@RequestParam("adultos") int adultos,
                                 @RequestParam("ninos") int ninos,
                                 @RequestParam("sillas") int sillas,
                                 @RequestParam("mesas") int mesas,
                                 @RequestParam("sombrillas") int sombrillas,
                                 Model model) {
        // Cálculo de subtotales
        int subtotalEntradasAdulto = adultos * 180;
        int subtotalEntradasNino = ninos * 120;
        int subtotalSillas = sillas * 30;
        int subtotalMesas = mesas * 50;
        int subtotalSombrillas = sombrillas * 50;
        
        // Cálculo total
        int total = subtotalEntradasAdulto + subtotalEntradasNino + subtotalSillas + subtotalMesas + subtotalSombrillas;
        
        // Pasar los datos al modelo para mostrarlos en la vista
        model.addAttribute("total", total);
        model.addAttribute("subtotalEntradasAdulto", subtotalEntradasAdulto);
        model.addAttribute("subtotalEntradasNino", subtotalEntradasNino);
        model.addAttribute("subtotalSillas", subtotalSillas);
        model.addAttribute("subtotalMesas", subtotalMesas);
        model.addAttribute("subtotalSombrillas", subtotalSombrillas);
        
        return "ticket"; // Redirige a la página del ticket
    }
}
