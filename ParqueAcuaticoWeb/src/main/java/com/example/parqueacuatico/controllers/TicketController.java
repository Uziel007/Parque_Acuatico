package com.example.parqueacuatico.controllers;

import com.example.parqueacuatico.models.DetalleCompra;
import com.example.parqueacuatico.services.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping("/ticket")
    public String mostrarTicket(Model model, @RequestParam double total, @RequestParam double pago) {
        // Obtener los detalles de la compra desde el servicio
        List<DetalleCompra> detalles = detalleCompraService.obtenerDetallesCompra();

        // Generar un código de barras único
        String codigoBarras = generarCodigoBarras();

        // Obtener la fecha actual
        Date fecha = new Date();

        // Añadir los atributos al modelo para la vista
        model.addAttribute("total", total);
        model.addAttribute("pago", pago);
        model.addAttribute("detalles", detalles);
        model.addAttribute("fecha", fecha);
        model.addAttribute("codigoBarras", codigoBarras);

        // Redirigir a la vista de ticket.html
        return "ticket";
    }

    // Método para generar un código de barras único
    private String generarCodigoBarras() {
        // Aquí puedes usar alguna librería para generar el código de barras
        // Por ejemplo, un número aleatorio como un código único
        return "1234567890"; // Ejemplo de código de barras
    }
}
