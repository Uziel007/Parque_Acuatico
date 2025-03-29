package com.example.parqueacuatico.controllers;

import com.example.parqueacuatico.models.Producto;
import com.example.parqueacuatico.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "compra"; // Asegúrate de que coincide con el nombre del archivo HTML
    }
}
